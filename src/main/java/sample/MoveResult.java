package sample;

public class MoveResult {

    private MoveType type;

    public MoveType getType() {
        return type;
    }

    private Ball ball;

    public Ball getBall() {
        return ball;
    }

    public MoveResult(MoveType type){
        this(type, null);
    }

    public MoveResult(MoveType type, Ball ball) {
        this.type = type;
        this.ball = ball;
    }
}
