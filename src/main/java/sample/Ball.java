package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static sample.BallsApp.SIZE;

public class Ball extends StackPane {

    private BallType type;

    public BallType getType() {
        return type;
    }

    public Ball(BallType type, int col, int row) {
        this.type = type;

        relocate(col * SIZE, row * SIZE);

        Circle circle = new Circle(SIZE / 3.5);
        circle.setFill(Color.valueOf(String.valueOf(type)));

//        circle.setStroke(Color.valueOf("LIGHT" + String.valueOf(type)));
//        circle.setStrokeWidth(SIZE * 0.05);

        circle.setTranslateX((SIZE - SIZE / 3.5 * 2)/2);
        circle.setTranslateY((SIZE - SIZE / 3.5 * 2)/2);



        getChildren().add(circle);
    }
}
