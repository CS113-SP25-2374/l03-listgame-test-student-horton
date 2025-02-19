package cs113.listGame.gamecore;

import cs113.listGame.resources.ResourceObject;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class GoalObject extends GameObject {

    protected GoalObject(ImageView imageView) {
        super(imageView);
    }

    @Override
    public void handleCollision(GameObject otherObject) {
        if (otherObject instanceof ResourceObject resource) {
            GameController.scoreResource(resource);
        }
    }
}
