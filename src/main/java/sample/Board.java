package sample;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Board {

    public static final int SIZE = 60;
    private static final int WIDTH = 7;
    private static final int HEIGHT = 7;
    private static final int LENGTH_TO_REMOVE = 3;
    private static final int BALLS_TO_ADD = 2;
    private static final int BALLS_TO_ADD_AFTER_MOVE = 3;
    private boolean deletedStatus = false;

    private final Tile[][] board = new Tile[WIDTH][HEIGHT];

    private final Group tileGroup = new Group();
    private final Group ballGroup = new Group();
    private int freeTiles = WIDTH * HEIGHT;


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

        addBalls(BALLS_TO_ADD);

        return root;
    }

    private void addBalls(int neededBalls) {
        int colAddingBall;
        int rowAddingBall;
//        int freeTiles = 0;
//
//        for (int row = 0; row < HEIGHT; row++) {
//            for (int col = 0; col < WIDTH; col++) {
//                if (!board[col][row].hasBall()) freeTiles++;
//            }
//        }
        if (freeTiles == 0) {

        } else {
            if (freeTiles < 5) {
                neededBalls = freeTiles;
            }
            for (int i = 0; i < neededBalls; ) {
                Ball ball;

                colAddingBall = new Random().nextInt(WIDTH);
                rowAddingBall = new Random().nextInt(HEIGHT);

                ball = makeBall(BallType.values()[new Random().nextInt(BallType.values().length)], colAddingBall, rowAddingBall);

                if (ball != null) {
                    if (!board[colAddingBall][rowAddingBall].hasBall()) {
                        ballGroup.getChildren().add(ball);
                        board[colAddingBall][rowAddingBall].setBall(ball);
//                        checkBalls(ball, 1, 0, true);
//                        checkBalls(ball, 0, 1, true);
//                        checkBallsCondition(ball, -1, 0, true);
//                        checkBallsCondition(ball, 0, -1, true);
//                        checkBallsCondition(ball, 1, 1, true);
//                        checkBallsCondition(ball, -1, 1, true);
//                        checkBallsCondition(ball, 1, -1, true);
//                        checkBallsCondition(ball, -1, -1, true);
                        i++;
                        freeTiles--;
                    }
                }
            }
        }

    }

    private MoveResult tryMove(Ball ball, int newX, int newY) {
        if (board[newX][newY].hasBall()) {
            return new MoveResult(MoveType.NONE);
        }

//        int x0 = toBoard(ball.getOldX());
//        int y0 = toBoard(ball.getOldY());
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
                    checkBalls(ball, 1, 0, true);
                    checkBalls(ball, 0, 1, true);
                    checkBalls(ball, -1, 0, true);
                    checkBalls(ball, 0, -1, true);
                    deletedStatus = false;
//                    checkBallsCondition(ball, 1, 1, true);
//                    checkBallsCondition(ball, -1, 1, true);
//                    checkBallsCondition(ball, 1, -1, true);
//                    checkBallsCondition(ball, -1, -1, true);
//                    removeBall(ball);
                    addBalls(BALLS_TO_ADD_AFTER_MOVE);
                    break;
            }
        });

        return ball;
    }

    private void removeBall(Ball ball) {
        int col = (int) (ball.getOldX() / SIZE);
        int row = (int) (ball.getOldY() / SIZE);
        if (board[col][row].hasBall()) {
            ballGroup.getChildren().remove(ball);
            board[col][row].setBall(null);
            freeTiles++;
        }
    }

    private boolean checkBalls(Ball ball, int colPosition, int rowPosition, boolean flag) {
        if(deletedStatus){}
        else {
            BallType ballType = ball.getType();
            int col = (int) (ball.getOldX() / SIZE);
            int row = (int) (ball.getOldY() / SIZE);
            int newCol = col;
            int newRow = row;
            int length = 1;

            boolean go = true;
            while (go) {
                if (newCol + colPosition < WIDTH && newCol + colPosition >= 0 && newRow + rowPosition < HEIGHT && newRow + rowPosition >= 0) {

                    if (board[newCol + colPosition][newRow + rowPosition].hasBall() && flag) {
                        newCol = newCol + colPosition;
                        newRow = newRow + rowPosition;
                        if (board[newCol][newRow].getBall().getType() == ballType) {

                        } else {
                            go = checkBalls(board[newCol - colPosition][newRow - rowPosition].getBall(), -colPosition, -rowPosition, false);
                        }
                    } else if (flag) {
                        go = checkBalls(board[newCol][newRow].getBall(), -colPosition, -rowPosition, false);
                    } else if (board[newCol + colPosition][newRow + rowPosition].hasBall() && !flag) {
                        newCol = newCol + colPosition;
                        newRow = newRow + rowPosition;
                        if (board[newCol][newRow].getBall().getType() == ballType) {
                            length++;
                        } else {
                            if (length >= LENGTH_TO_REMOVE) {
                                sayToRemoveBalls(length, colPosition, rowPosition, ball);
                            }
                            go = false;
                        }
                    } else {
                        if (length >= LENGTH_TO_REMOVE) {
                            sayToRemoveBalls(length, colPosition, rowPosition, ball);
                        }
                        go = false;
                    }
                } else if (flag) {
                    go = checkBalls(board[newCol][newRow].getBall(), -colPosition, -rowPosition, false);
                } else {
                    if (length >= LENGTH_TO_REMOVE) {
                        sayToRemoveBalls(length, colPosition, rowPosition, ball);
                    }
                    go = false;
                }
            }
        }
        return flag;
    }

    private void sayToRemoveBalls(int length, int colPosition, int rowPosition, Ball ball) {
        for (int i = 0; i < length; i++) {
            if (colPosition == 1 && rowPosition == 1)
                removeBall(board[(int) ball.getOldX() / SIZE + i][(int) ball.getOldY() / SIZE + i].getBall());
            else if (colPosition == 1 && rowPosition == -1)
                removeBall(board[(int) ball.getOldX() / SIZE + i][(int) ball.getOldY() / SIZE - i].getBall());
            else if (colPosition == -1 && rowPosition == 1)
                removeBall(board[(int) ball.getOldX() / SIZE - i][(int) ball.getOldY() / SIZE + i].getBall());
            else if (colPosition == -1 && rowPosition == -1)
                removeBall(board[(int) ball.getOldX() / SIZE - i][(int) ball.getOldY() / SIZE - i].getBall());
            else if (colPosition == 1)
                removeBall(board[((int) ball.getOldX() / SIZE + i)][(int) ball.getOldY() / SIZE].getBall());
            else if (rowPosition == 1)
                removeBall(board[(int) ball.getOldX() / SIZE][(int) ball.getOldY() / SIZE + i].getBall());
            else if (colPosition == -1)
                removeBall(board[(int) ball.getOldX() / SIZE - i][(int) ball.getOldY() / SIZE].getBall());
            else if (rowPosition == -1)
                removeBall(board[(int) ball.getOldX() / SIZE][(int) ball.getOldY() / SIZE - i].getBall());
            deletedStatus = true;
        }
    }


    private int toBoard(double pixel) {
        return (int) ((pixel + SIZE / 2) / SIZE);
    }
}
