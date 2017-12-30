package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

public class BallsApp extends Application {

    public static final int SIZE = 60;
    public static final int WIDTH = 7;
    public static final int HEIGHT = 7;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group ballGroup = new Group();

    private Parent createContent() {
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

    private void addBalls(int amount) {
        int colBall;
        int rowBall;
        int count = 0;

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (!board[col][row].hasBall()) count++;
            }
        }
        if (count == 0) {

        } else {
            if (count < 5) {
                amount = count;
            }
            for (int i = 0; i < amount; ) {
                Ball ball = null;

                colBall = new Random().nextInt(WIDTH);
                rowBall = new Random().nextInt(HEIGHT);

                ball = makeBall(BallType.values()[new Random().nextInt(BallType.values().length)], colBall, rowBall);

                if (ball != null) {
                    if (!board[colBall][rowBall].hasBall()) {
                        ballGroup.getChildren().add(ball);
                        board[colBall][rowBall].setBall(ball);
                        i++;
                    }
                }
            }
        }

    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
//        primaryStage.setTitle("BallsApp");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("BallsApp");
        primaryStage.setScene(scene);
        primaryStage.show();

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

    private MoveResult tryMove(Ball ball, int newX, int newY) {
        if (board[newX][newY].hasBall()) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(ball.getOldX());
        int y0 = toBoard(ball.getOldY());
        return new MoveResult(MoveType.NORMAL);
    }

    private int toBoard(double pixel) {
        return (int) ((pixel + SIZE / 2) / SIZE);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
