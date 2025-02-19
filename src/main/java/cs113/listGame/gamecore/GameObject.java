package cs113.listGame.gamecore;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.concurrent.TimeUnit;

public abstract class GameObject {
    ImageView imageView;

    Point2D delta;
    Point2D position;
    Point2D echoPosition;
    long echoTime;
    protected double speed = 2.0;
    protected double settleDistance = 30.0;
    protected long echoResetTime = TimeUnit.MILLISECONDS.toNanos(0);

    public void setDeltaX(double dX) { this.delta = new Point2D(dX, this.delta.getY()); }
    public void setDeltaY(double dY) { this.delta = new Point2D(this.delta.getX(), dY); }
    public void setDelta(Point2D delta) { this.delta = delta; }
    public void setPosition(Point2D pos) { this.position = pos; }

    public Bounds getBounds() { return imageView.getBoundsInParent(); }
    public Point2D getWorldCenter() { return new Point2D(getBounds().getCenterX(), getBounds().getCenterY()); }
    public Point2D getBoundCenter() { return new Point2D(getBounds().getWidth() * 0.5, getBounds().getHeight() * 0.5); }
    public Point2D getEchoCenter() { return echoPosition.add(getBoundCenter()); }

    protected GameObject(ImageView imageView) {
        this.imageView = imageView;
        this.position = new Point2D(imageView.getLayoutX(), imageView.getLayoutY());
        this.delta = new Point2D(0.0, 0.0);
    }

    /**
     * Checks for a collision
     * @param otherObject to check against
     * @return true if they are in collision
     */
    public boolean checkForCollision(GameObject otherObject) {
        if (this == otherObject || otherObject == null) return false;
        return getBounds().intersects(otherObject.getBounds());
    }

    public boolean moveTowards(Point2D point) {
        Point2D toVector = point.subtract(this.getWorldCenter());

        if (toVector.magnitude() > this.settleDistance) {
            if (toVector != Point2D.ZERO) {
                toVector = toVector.normalize();
            }
            this.delta = toVector.multiply(speed);
        }
        else {
            this.delta = Point2D.ZERO;
        }

        return this.delta != Point2D.ZERO;
    }

    public boolean moveTowards(GameObject otherObject) {
        return moveTowards(otherObject.echoPosition);
    }

    /**
     * Handles a collision with another object
     * @param otherObject the object that was collided with
     */
    public abstract void handleCollision(GameObject otherObject);

    /**
     * Performs game logic for the character
     */
    public void update(long now) {
        if (delta != Point2D.ZERO) {
            delta = delta.normalize();
            delta = delta.multiply(speed);
            position = position.add(delta);
            delta = Point2D.ZERO;

            // occasionally leave an echo so the follow is 'steppy'
            if ((now - echoTime) > echoResetTime) {
                echoTime = now;
                echoPosition = position;
            }
        }

        imageView.setLayoutX(position.getX());
        imageView.setLayoutY(position.getY());
    }
}
