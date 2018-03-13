package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Score {
    int score = 0;

    Rectangle rectangle;
    Text scoreText;
    StackPane stackPane;

    public Score(int width, int height, String text) {
        rectangle = new Rectangle(width, height);
        scoreText = new Text(text);
        rectangle.setFill(Color.valueOf("white"));
        rectangle.setStroke(Color.BLACK);
        stackPane = new StackPane(rectangle, scoreText);
    }

    public void setLayout(double x, double y) {
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
    }

    public void changeScore(int score) {
        scoreText.setText(Integer.toString(score));
    }

    public double getWidth() {
        return rectangle.getWidth();
    }
}
