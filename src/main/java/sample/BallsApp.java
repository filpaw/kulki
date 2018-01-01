package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

import static sample.Board.HEIGHT;
import static sample.Board.SIZE;
import static sample.Board.WIDTH;

public class BallsApp extends Application {







//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
//        primaryStage.setTitle("BallsApp");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }
    private Board board = new Board();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(board.createContent());
        primaryStage.setTitle("BallsApp");
        primaryStage.setScene(scene);
        primaryStage.show();

    }








    public static void main(String[] args) {
        launch(args);
    }
}
