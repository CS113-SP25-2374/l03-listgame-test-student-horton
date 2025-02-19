package cs113.listGame.behaviors;

import cs113.listGame.gamecore.GameController;
import cs113.listGame.gamecore.GameObject;

public class FollowObject extends Behavior {
    GameObject followObject;

    public FollowObject(GameObject gameObject, long behaviorMillis, GameObject followObject) {
        super(gameObject, behaviorMillis);
        this.followObject = followObject;
    }

    @Override
    public void update(long now) {
        GameController.debugText("Following!");
        getGameObject().moveTowards(followObject.getWorldCenter());
    }
}
