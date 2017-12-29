package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;

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
        setWidth(BallsApp.SIZE);
        setHeight(BallsApp.SIZE);
        setStroke(Color.LIGHTGRAY);
        setArcHeight(15);
        setArcWidth(15);

        relocate(col * BallsApp.SIZE, row * BallsApp.SIZE);

        setFill(Color.WHEAT);
    }
}
