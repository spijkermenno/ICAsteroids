package mennospijker.icasteroids;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Player.
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects {
    private Timer timer;
    private ICAstroids world;
    private int size, DefaultX, DefaultY, points = 0;
    private boolean left, right, shoot;
    private final int speed = 5;
    private String weaponType = "red";
    private int weaponTypeSet = 0, getWeaponTypeReset = 10;
    /**
     * The Laser beams.
     */
    ArrayList<Weapon> laserBeams = new ArrayList<>();
    /**
     * The To delete.
     */
    ArrayList<Weapon> toDelete = new ArrayList<>();
    private boolean isTimerSet = false;


    /**
     * Instantiates a new Player.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     */
    Player(ICAstroids world, int x, int y) {
        super(world.loadSprite("spaceshipFlyInverse.png"), 2);
        setCurrentFrameIndex(1);
        this.world = world;
        this.size = (int) (getWidth() / 2);
        setFriction(0.1f);
    }

    /**
     * Sets default.
     *
     * @param x the x
     * @param y the y
     */
    public void setDefault(int x, int y) {
        this.DefaultX = x;
        this.DefaultY = y;
    }

    @Override
    public void update() {
        checkLocation();
        keyHandler();
        checkLaserPosition();
        weaponType();
    }

    /**
     * Sets laser timer.
     */
    void setLaserTimer() {
        if (isTimerSet) {
            timer.cancel();
            isTimerSet = false;
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                weaponTypeSet++;
            }
        }, 1000, 1000);
        isTimerSet = true;
    }

    private void weaponType() {
        if (weaponType.equals("blue")) {
            if (weaponTypeSet > getWeaponTypeReset) {
                weaponType = "red";
                timer.cancel();
            }
        }
    }

    private void checkLaserPosition() {
        boolean deleteFirstLaser = false;

        for (Weapon l : laserBeams) {
            if (l.getY() < 0) {
                deleteFirstLaser = true;
            }
        }

        if (deleteFirstLaser) {
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
            if (!world.getGameState()) {
                world.startGame();
            } else {
                if (laserBeams.size() < 10) {
                    Weapon l;
                    switch (weaponType) {
                        case "blue":
                            l = new BlueLaser(world, this, (int) (getCenterX() - 5), (int) getCenterY());
                            break;
                        default:
                            l = new RedLaser(world, this, (int) (getCenterX() - 5), (int) getCenterY());
                            break;
                    }
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

    /**
     * Reset.
     */
    void reset() {
        setX(DefaultX);
        setY(DefaultY);
        for (Weapon l : laserBeams) {
            world.deleteGameObject(l);
        }
        laserBeams.clear();
        points = 0;
    }

    /**
     * Sets points.
     *
     * @param points the points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Add points.
     *
     * @param points the points
     */
    public void addPoints(int points) {
        if (this.points == 0) {
            setPoints(points);
        } else {
            this.points += points;
        }
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    int getPoints() {
        if (this.points == 0) {
            return 0;
        }
        return this.points;
    }

    /**
     * Remove laser beam.
     *
     * @param laser the laser
     */
    public void removeLaserBeam(Weapon laser) {
        this.laserBeams.remove(laser);
    }

    /**
     * Sets laser type.
     *
     * @param type the type
     */
    public void setLaserType(String type) {
        switch (type) {
            case "red":
                this.weaponType = type;
                timer.cancel();
                break;
            case "blue":
                this.weaponType = type;
                setLaserTimer();
                break;
        }
    }
}
