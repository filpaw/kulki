package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static sample.Board.SIZE;


public class Ball extends StackPane {

    private BallType type;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public BallType getType() {
        return type;
    }

    public double getOldY() {
        return oldY;
    }

    public double getOldX() {
        return oldX;
    }

    public Ball(BallType type, int col, int row) {
        this.type = type;

        move(col, row);

        Circle circle = new Circle(SIZE / 3.5);
        circle.setFill(Color.valueOf(String.valueOf(type)));

//        circle.setStroke(Color.valueOf("LIGHT" + String.valueOf(type)));
//        circle.setStrokeWidth(SIZE * 0.05);

        circle.setTranslateX((SIZE - SIZE / 3.5 * 2) / 2);
        circle.setTranslateY((SIZE - SIZE / 3.5 * 2) / 2);

        getChildren().add(circle);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });
    }

    public void move(int col, int row) {
        oldX = col * SIZE;
        oldY = row * SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }


}
