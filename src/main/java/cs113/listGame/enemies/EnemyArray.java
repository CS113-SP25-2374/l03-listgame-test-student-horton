package cs113.listGame.enemies;

import cs113.listGame.behaviors.Behavior;
import cs113.listGame.behaviors.MoveToPoint;
import cs113.listGame.behaviors.Waiting;
import cs113.listGame.resources.ResourceObject;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.Random;

public class EnemyArray extends EnemyObject {
    Behavior[] behaviors = new Behavior[2];
    Random random = new Random();

    public EnemyArray(ImageView imageView) { super(imageView); }

    @Override
    public void seedBehaviors() {
        Point2D dest = new Point2D(random.nextDouble(500), random.nextDouble(400));

        behaviors[0] = new Waiting(this, 3000);
        behaviors[1] = new MoveToPoint(this, 4000, dest);
    }

    @Override
    public Behavior getNextBehavior() {
        int count = 0;

        for (int i = 0; i < behaviors.length && behaviors[i] != null; i++) {
            count++;
        }

        if (count == 0) return null;

        int index = random.nextInt(count);
        Behavior behavior = behaviors[index];
        behaviors[index] = null;

        for (int i = 0; i < behaviors.length - 1; i++) {
            if (behaviors[i] == null) {
                behaviors[i] = behaviors[i+1];
            }
        }

        return behavior;
    }
}
