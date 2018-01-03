package sample;

public class MoveResult {

    private final MoveType type;

    public MoveType getType() {
        return type;
    }

    private final Ball ball;

    public Ball getBall() {
        return ball;
    }

    public MoveResult(MoveType type){
        this(type, null);
    }

    private MoveResult(MoveType type, Ball ball) {
        this.type = type;
        this.ball = ball;
    }
}
