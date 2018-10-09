package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;

/**
 * The type Player.
 */
public class Player extends AnimatedSpriteObject {
    private ICAstroids world;
    private int size, frame = 0, loop = 0;
    private boolean left, right, shoot;
    private final int speed = 5;

    /**
     * Instantiates a new Player.
     *
     * @param world the world
     */
    Player(ICAstroids world) {
        super(world.loadSprite("spaceshipFly.png"), 2);
        setCurrentFrameIndex(1);
        this.world = world;
        this.size = (int) (getWidth() / 2);
        setFriction(0.1f);
    }

    @Override
    public void update() {
        if (loop == 30) {
            if (frame == 0) {
                setCurrentFrameIndex(1);
                frame = 1;
            } else {
                setCurrentFrameIndex(0);
                frame = 0;
            }
            loop = 0;
        } else {
            loop++;
        }

        // When player outside world borders:
        // - set speed to 0
        // - set position to border minus size.
        if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }

        // When player collides with world borders:
        // - set speed to 0
        // - set position to border minus size.
        if (getX() >= world.width - size) {
            setxSpeed(0);
            setX(world.width - size);
        }
        if (getY() >= world.height - size) {
            setySpeed(0);
            setY(world.height - size);
        }

        if (getSpeed() < 0.5) {
            setCurrentFrameIndex(0);
        }

        if (right) {
            setDirectionSpeed(90, speed);
            //setCurrentFrameIndex(1);
        }

        if (left) {
            setDirectionSpeed(270, speed);
            //setCurrentFrameIndex(3);
        }
        if (shoot) {
            System.out.println("SCHIET!");
            shoot = false;
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
    int[] getLocation(){
        int[] playerLocation = new int[2];
        playerLocation[0] = (int) getCenterX() - (int) (this.size / 2) - 20;
        playerLocation[1] = (int) getCenterX() + (int) (this.size / 2) + 20;
        return playerLocation;
    }
}
