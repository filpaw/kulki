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
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group ballGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * SIZE, HEIGHT * SIZE);
        root.getChildren().addAll(tileGroup, ballGroup);

        int colBall;
        int rowBall;
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Tile tile = new Tile(col, row);
                board[col][row] = tile;

                tileGroup.getChildren().addAll(tile);
            }
        }

        for (int i = 0; i < 5; i++) {
            Ball ball = null;

            colBall = new Random().nextInt(WIDTH);
            rowBall = new Random().nextInt(HEIGHT);

            ball = makeBall(BallType.values()[new Random().nextInt(BallType.values().length)], colBall, rowBall);

            if (ball != null) {
                if(!board[colBall][rowBall].hasBall()){
                    ballGroup.getChildren().add(ball);
                    board[colBall][rowBall].setBall(ball);
                }
            }
        }

        return root;
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
        Ball ball = new Ball(type, col, row);

        return ball;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
