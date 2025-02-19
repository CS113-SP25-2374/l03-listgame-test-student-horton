package cs113.listGame.enemies;

import cs113.listGame.behaviors.Behavior;
import javafx.scene.image.ImageView;

public class EnemyLinkedList extends EnemyObject {
    public EnemyLinkedList(ImageView imageView) { super(imageView); }

    @Override
    public void seedBehaviors() {

    }

    @Override
    public Behavior getNextBehavior() {
        return null;
    }
}
