package mennospijker.icasteroids;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Player.
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects {
    private ICAstroids world;
    private int size, DefaultX, DefaultY, points = 0;
    private boolean left, right, shoot;
    private final int speed = 5;
    ArrayList<Weapon> laserBeams = new ArrayList<>();
    ArrayList<Weapon> toDelete = new ArrayList<>();


    /**
     * Instantiates a new Player.
     *
     * @param world the world
     */
    Player(ICAstroids world, int x, int y) {
        super(world.loadSprite("spaceshipFlyInverse.png"), 2);
        setCurrentFrameIndex(1);
        this.world = world;
        this.size = (int) (getWidth() / 2);
        setFriction(0.1f);
    }

    public void setDefault(int x, int y){
        this.DefaultX = x;
        this.DefaultY = y;
    }

    @Override
    public void update() {
        checkLocation();
        keyHandler();
        checkLaserPosition();
    }

    private void checkLaserPosition(){
        boolean deleteFirstLaser = false;

        for (Weapon l : laserBeams) {
            if (l.getY() < 0) {
                deleteFirstLaser = true;
            }
        }

        if (deleteFirstLaser){
            laserBeams.remove(0);
        }
    }

    private void keyHandler() {
        if (right) {
            setDirectionSpeed(90, speed);
        }

        if (left) {
            setDirectionSpeed(270, speed);
        }

        if (shoot) {
            if (!world.getGameState()){
                world.startGame();;
            }else {
                if (laserBeams.size() < 10) {
                    RedLaser l = new RedLaser(world, this, (int) (getCenterX() - 5), (int) getCenterY());
                    laserBeams.add(l);
                    world.addGameObject(l, (getCenterX() - 5), getCenterY());
                }
            }
            shoot = false;
        }
    }

    private void checkLocation() {
        if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }

        if (getX() >= world.width - size) {
            setxSpeed(0);
            setX(world.width - size);
        }
        if (getY() >= world.height - size) {
            setySpeed(0);
            setY(world.height - size);
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        setState(keyCode, key, true);
    }

    private void setState(int keyCode, char key, boolean b) {
        if (keyCode == world.LEFT || key == 'a') {
            left = b;
        }
        if (keyCode == world.RIGHT || key == 'd') {
            right = b;
        }
        if (key == ' ') {
            shoot = b;
        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {
        setState(keyCode, key, false);
    }

    /**
     * Get location int [ ].
     *
     * @return the int [ ]
     */
    int[] getLocation() {
        int[] playerLocation = new int[2];
        playerLocation[0] = (int) getCenterX() - (int) (this.size / 2) - 20;
        playerLocation[1] = (int) getCenterX() + (int) (this.size / 2) + 20;
        return playerLocation;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {
        for (GameObject g : list) {
            if (g instanceof Asteroid) {
                world.stopGame();
            }
        }
    }
    void reset() {
        setX(DefaultX);
        setY(DefaultY);
        for (Weapon l : laserBeams){
            world.deleteGameObject(l);
        }
        laserBeams.clear();
        points = 0;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points){
        if (this.points == 0){
            setPoints(points);
        }else {
            this.points += points;
        }
    }

    int getPoints(){
        if (this.points == 0) {
            return 0;
        }
        return this.points;
    }

    public void removeLaserBeam(RedLaser redLaser) {
        this.laserBeams.remove(redLaser);
    }
}
