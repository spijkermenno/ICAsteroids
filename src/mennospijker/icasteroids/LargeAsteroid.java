package mennospijker.icasteroids;

/**
 * The type Asteroid.
 */
public class LargeAsteroid extends Asteroid {

    /**
     * Instantiates a new Asteroid.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     */
    LargeAsteroid(ICAstroids world, int x, int y, int speed) {
        super(world.loadSprite("spriteLargeAstroid.png"), 8, world);
        setX(x);
        setY(y);
        setDirectionSpeed(180, speed);
        setTimerTime(500);
        setFrameTimer();
        this.health = 2;
        this.value = 3;
        this.world = world;
    }

    @Override
    public void update() {

    }
}
