package cs113.listGame.behaviors;

import cs113.listGame.gamecore.GameController;
import cs113.listGame.gamecore.GameObject;
import javafx.geometry.Point2D;

public class Waiting extends Behavior {
    long behaviorEnd;

    public Waiting(GameObject gameObject, long behaviorMillis) {
        super(gameObject, behaviorMillis);
    }

    @Override
    public void update(long now) {
        GameController.debugText("Waiting: " + (behaviorEnd - now));
        getGameObject().setDelta(Point2D.ZERO);
    }
}
