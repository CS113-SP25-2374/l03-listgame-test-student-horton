package cs113.listGame.helpers;

import javafx.scene.image.ImageView;

public class JavaFXHelper {
    public static ImageView cloneImageView(ImageView original) {
        // Create a new ImageView with the same image as the original
        ImageView clone = new ImageView(original.getImage());

        // Copy properties (width, height, etc.)
        clone.setFitWidth(original.getFitWidth());
        clone.setFitHeight(original.getFitHeight());
        clone.setPreserveRatio(original.isPreserveRatio());
        clone.setSmooth(original.isSmooth());
        clone.setOpacity(original.getOpacity());
        clone.setRotate(original.getRotate());

        // If there are transformations, copy them
        clone.setTranslateX(original.getTranslateX());
        clone.setTranslateY(original.getTranslateY());

        return clone;
    }
}
