package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static sample.Board.SIZE;

public class Tile extends Rectangle {

    private Ball ball;

    public boolean hasBall() {
        return ball != null;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Tile(int col, int row) {
        setWidth(SIZE);
        setHeight(SIZE);
        setStroke(Color.LIGHTGRAY);
        setArcHeight(15);
        setArcWidth(15);

        relocate(col * SIZE, row * SIZE);

        setFill(Color.WHEAT);

        setOnMouseClicked(e -> {
            BallType ballType;
            try {
                ballType = getBall().getType();
            } catch (Exception u) {
                ballType = null;
            }
            System.out.println((col + 1) + " " + (row + 1 + " ball: " + ballType));
        });
    }
}
