package cs113.listGame.behaviors;

import cs113.listGame.gamecore.GameController;
import cs113.listGame.gamecore.GameObject;
import javafx.geometry.Point2D;

public class MoveToPoint extends Behavior {
    Point2D target;

    public MoveToPoint(GameObject gameObject, long behaviorMillis, Point2D target) {
        super(gameObject, behaviorMillis);
        this.target = target;
    }

    @Override
    public void update(long now) {
        GameController.debugText("Moving!");
        getGameObject().moveTowards(target);
    }
}