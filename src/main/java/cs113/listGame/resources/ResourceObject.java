package cs113.listGame.resources;

import cs113.listGame.gamecore.GameObject;
import javafx.scene.image.ImageView;

public class ResourceObject extends GameObject {
    public ResourceObject(ImageView imageView) {
        super(imageView);
    }

    @Override
    public void handleCollision(GameObject otherObject) {
        // do nothing, as this is a passive object
    }
}
