package cs113.listGame.enemies;

import cs113.listGame.gamecore.GameController;
import cs113.listGame.gamecore.GameObject;
import cs113.listGame.behaviors.Behavior;
import cs113.listGame.resources.ResourceObject;
import javafx.scene.image.ImageView;

public abstract class EnemyObject extends GameObject {
    Behavior currentBehavior;

    protected EnemyObject(ImageView imageView) {
        super(imageView);
    }

    @Override
    public void update(long now) {
        super.update(now); // handles general behavior, like locomotion

        if (currentBehavior == null) { // try to find a new behavior
            currentBehavior = getNextBehavior();

            if (currentBehavior == null) { // no behaviors ready, so seed the list
                seedBehaviors();
            } else {
                currentBehavior.start(now);
            }

        } else {
            currentBehavior.update(now);

            if (!currentBehavior.isActive(now))
            {
                currentBehavior = null;
            }
        }
    }

    @Override
    public void handleCollision(GameObject otherObject) {
        if (otherObject instanceof ResourceObject resourceObject) {
            GameController.getPlayer().loseResourceAfter(resourceObject);
        }
    }

    public abstract void seedBehaviors();
    public abstract Behavior getNextBehavior();
}
