package sample;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Board {

    public static final int SIZE = 60;
    public static final int WIDTH = 7;
    public static final int HEIGHT = 7;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group ballGroup = new Group();



    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * SIZE, HEIGHT * SIZE);
        root.getChildren().addAll(tileGroup, ballGroup);


        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Tile tile = new Tile(col, row);
                board[col][row] = tile;

                tileGroup.getChildren().addAll(tile);
            }
        }

        addBalls(5);

        return root;
    }

    private void addBalls(int neededBalls) {
        int colAddingBall;
        int rowAddingBall;
        int freeTiles = 0;

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (!board[col][row].hasBall()) freeTiles++;
            }
        }
        if (freeTiles == 0) {

        } else {
            if (freeTiles < 5) {
                neededBalls = freeTiles;
            }
            for (int i = 0; i < neededBalls; ) {
                Ball ball = null;

                colAddingBall = new Random().nextInt(WIDTH);
                rowAddingBall = new Random().nextInt(HEIGHT);

                ball = makeBall(BallType.values()[new Random().nextInt(BallType.values().length)], colAddingBall, rowAddingBall);

                if (ball != null) {
                    if (!board[colAddingBall][rowAddingBall].hasBall()) {
                        ballGroup.getChildren().add(ball);
                        board[colAddingBall][rowAddingBall].setBall(ball);
                        i++;
                    }
                }
            }
        }

    }

    private MoveResult tryMove(Ball ball, int newX, int newY) {
        if (board[newX][newY].hasBall()) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(ball.getOldX());
        int y0 = toBoard(ball.getOldY());
        return new MoveResult(MoveType.NORMAL);
    }

    private Ball makeBall(BallType type, int col, int row) {
        final Ball ball = new Ball(type, col, row);

        ball.setOnMouseReleased(e -> {
            int newX = toBoard(ball.getLayoutX());
            int newY = toBoard(ball.getLayoutY());

            MoveResult result = tryMove(ball, newX, newY);

            int x0 = toBoard(ball.getOldX());
            int y0 = toBoard(ball.getOldY());

            switch (result.getType()) {
                case NONE:
                    ball.abortMove();
                    break;
                case NORMAL:
                    ball.move(newX, newY);
                    board[x0][y0].setBall(null);
                    board[newX][newY].setBall(ball);
                    addBalls(3);
                    break;
            }
        });

        return ball;
    }

    private int toBoard(double pixel) {
        return (int) ((pixel + SIZE / 2) / SIZE);
    }
}
