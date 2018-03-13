package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static com.sun.javafx.scene.control.skin.Utils.getResource;


public class BallsApp extends Application {

    //    private final Meni menu = new Meni();
    private final Board board = new Board();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create menuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu gameMenu = new Menu("Game");
        Menu optionsMenu = new Menu("Options");
        Menu helpMenu = new Menu("Help");

        //Create game items
        MenuItem newItem = new MenuItem("New");
        MenuItem scoreItem = new MenuItem("Score");
        MenuItem exitItem = new MenuItem("Exit");

        // Create options items
        MenuItem skinItem = new MenuItem("Skin");
        MenuItem langItem = new MenuItem("Language");

//        // Create skinItems
//        MenuItem darkSkinItem = new MenuItem("SkinDark");
//        MenuItem lightSkinItem = new MenuItem("SkinLight");
//        MenuItem classicSkinItem = new MenuItem("SkinClassic");
//
//        // add skinItems to skin

        // Add menuItems to menus
        gameMenu.getItems().addAll(newItem, scoreItem, exitItem);
        optionsMenu.getItems().addAll(skinItem, langItem);

        // Add menus to menuBar
        menuBar.getMenus().addAll(gameMenu, optionsMenu, helpMenu);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(board.createContent());


//        Scene menuScene = new Scene(menu.createMenu());
        Scene scene = new Scene(root, 500, 300);
        Scene menu = sceneMenu();

        String css = this.getClass().getResource("/menu.css").toExternalForm();
        menu.getStylesheets().clear();
        menu.getStylesheets().add(css);

        primaryStage.setTitle("BallsApp");
        primaryStage.setScene(menu);

        primaryStage.show();

//        menu.submit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.setScene(sceneBoard);
//                primaryStage.show();
//            }
//        });
//
//        board.back.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.setScene(menuScene);
//                primaryStage.show();
//            }
//        });
    }

    private Scene sceneMenu() {
        int prefWidth = 70;
        int prefHeight = 50;
        double width = board.getPrefWidth();
        double height = board.getPrefHeight();

        BorderPane menus = new BorderPane();
        menus.setId("menus");

        Button playBtn = new Button("Play");
        Button skinBtn = new Button("Skins");
        Button helpBtn = new Button("Help");
        Button scoresBtn = new Button("Scores");

        playBtn.setId("play-btn");
        skinBtn.setId("skin-btn");
        helpBtn.setId("help-btn");
        scoresBtn.setId("scores-btn");

        HBox hbox = new HBox(playBtn, skinBtn, helpBtn, scoresBtn);
        hbox.setAlignment(Pos.CENTER);

        menus.setCenter(hbox);


        Scene scene = new Scene(menus, width, height);

        return scene;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
