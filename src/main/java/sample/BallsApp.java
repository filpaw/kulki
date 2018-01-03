package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BallsApp extends Application {







//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
//        primaryStage.setTitle("BallsApp");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }
    private final Board board = new Board();

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
