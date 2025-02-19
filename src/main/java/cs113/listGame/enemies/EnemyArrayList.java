package cs113.listGame.enemies;

import cs113.listGame.behaviors.Behavior;
import cs113.listGame.gamecore.GameObject;
import javafx.scene.image.ImageView;

public class EnemyArrayList extends EnemyObject {
    public EnemyArrayList(ImageView imageView) { super(imageView); }

    @Override
    public void seedBehaviors() {

    }

    @Override
    public Behavior getNextBehavior() {
        return null;
    }
}