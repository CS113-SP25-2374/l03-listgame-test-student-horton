package cs113.listGame.gamecore;

import cs113.listGame.enemies.*;
import cs113.listGame.helpers.JavaFXHelper;
import cs113.listGame.resources.ResourceObject;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameController {
    static GameController instance;
    public static void debugText(String text) { instance.debugLabel.setText(text); }
    public static void scoreResource(ResourceObject resource) { instance.completeScore(resource); }

    @FXML
    private ImageView playerImage;

    @FXML
    private ImageView resourceImageA;

    @FXML
    private ImageView resourceImageB;

    @FXML
    private ImageView resourceImageC;

    @FXML
    private ImageView enemyImageA;

    @FXML
    private ImageView enemyImageB;

    @FXML
    private ImageView enemyImageC;

    @FXML
    private ImageView enemyImageD;

    @FXML
    private ImageView goalImage;

    @FXML
    private Label debugLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Pane gamePane;

    ImageView[] resourceImages;

    private PlayerObject player;
    static public PlayerObject getPlayer() { return instance.player; }

    List<GameObject> gameObjectList;
    List<EnemyObject> gameEnemyList;

    AnimationTimer gameLoop;
    Random random;
    int score;

    private void completeScore(ResourceObject resource) {
        resource.setPosition(new Point2D(1000,1000));
        scoreLabel.setText(Integer.toString(++score));
        player.loseResource(resource);
    }

    public void initialize() {
        instance = this;
        gamePane.setFocusTraversable(true);
        gamePane.requestFocus();

        random = new Random();

        gameObjectList = new ArrayList<>();
        gameEnemyList = new ArrayList<>();

        resourceImages = new ImageView[3];
        resourceImages[0] = resourceImageA;
        resourceImages[1] = resourceImageB;
        resourceImages[2] = resourceImageC;
        for (int i = 0; i < resourceImages.length; i++) {
            resourceImages[i].setVisible(false);
        }

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateAll(now);
                handleCollisions();
            }
        };

        startGame();
    }

    public void startGame() {
        gameObjectList.clear();

        player = new PlayerObject(playerImage, new Point2D(75, 75));
        gameObjectList.add(player);

        EnemyObject enemyA = new EnemyArrayList(enemyImageA);
        gameEnemyList.add(enemyA);

        EnemyObject enemyB = new EnemyLinkedList(enemyImageB);
        gameEnemyList.add(enemyB);

        EnemyObject enemyC = new EnemyDequeStack(enemyImageC);
        gameEnemyList.add(enemyC);

        EnemyObject enemyD = new EnemyArray(enemyImageD);
        gameEnemyList.add(enemyD);

        GoalObject goal = new GoalObject(goalImage);
        gameObjectList.add(goal);

        gameObjectList.addAll(gameEnemyList);

        createResources(10);
        gameLoop.start();
    }

    public void stopGame() {
        gameLoop.stop();
    }

    public void createResources(int count) {
        Bounds bounding = gamePane.getBoundsInParent();

        for (int i = 0; i < count; i++) {
            ImageView imageView = JavaFXHelper.cloneImageView(resourceImages[random.nextInt(resourceImages.length)]);

            // add to this list for automatic drawing
            gamePane.getChildren().add(imageView);

            // randomly position the seed location
            ResourceObject resource = new ResourceObject(imageView);
            double x = bounding.getMinX() + (random.nextDouble() * (bounding.getWidth() - imageView.getFitWidth()));
            double y = bounding.getMinY() + (random.nextDouble() * (bounding.getHeight() - imageView.getFitHeight()));
            resource.setPosition(new Point2D(x, y));

            // add to this list for automatic updating & colliding
            gameObjectList.add(resource);
        }
    }

    public void updateAll(long now) {
        Iterator<GameObject> iterator = gameObjectList.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(now);
        }
    }

    public void handleCollisions() {
        for (GameObject gameObject : gameObjectList) {
            for (GameObject secondObject : gameObjectList) {
                if (gameObject.checkForCollision(secondObject)) {
                    gameObject.handleCollision(secondObject);
                }
            }
        }
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP: player.setDeltaY(-1.25); break;
            case DOWN: player.setDeltaY(1.25); break;
            case LEFT: player.setDeltaX(-1.25); break;
            case RIGHT: player.setDeltaX(1.25); break;
            default: break;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP, DOWN: player.setDeltaY(0); break;
            case LEFT, RIGHT: player.setDeltaX(0); break;
            default: break;
        }
    }
}