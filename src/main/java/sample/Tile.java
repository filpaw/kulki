package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;

import static sample.Board.SIZE;

public class Tile extends Rectangle{

    private Ball ball;

    public boolean hasBall(){
        return ball != null;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball){
        this.ball = ball;
    }

    public Tile(int col, int row){
        setWidth(SIZE);
        setHeight(SIZE);
        setStroke(Color.LIGHTGRAY);
        setArcHeight(15);
        setArcWidth(15);

        relocate(col * SIZE, row * SIZE);

        setFill(Color.WHEAT);
    }
}
