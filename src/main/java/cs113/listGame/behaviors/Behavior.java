package cs113.listGame.behaviors;

import cs113.listGame.gamecore.GameObject;

import java.util.concurrent.TimeUnit;

public abstract class Behavior {
    private GameObject gameObject;
    private long behaviorEndTime;
    private long behaviorTotalTime;

    public boolean isActive(long now) {
        return now < this.behaviorEndTime;
    }

    public GameObject getGameObject() { return this.gameObject; }

    protected Behavior(GameObject gameObject, long behaviorMillis) {
        this.gameObject = gameObject;
        this.behaviorTotalTime = TimeUnit.MILLISECONDS.toNanos(behaviorMillis);
    }

    public void start(long now) {
        this.behaviorEndTime = now + this.behaviorTotalTime;
    }

    public abstract void update(long now);
}