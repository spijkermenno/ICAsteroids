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
     * @param speed the speed
     */
    LargeAsteroid(ICAstroids world, int x, int y, float speed) {
        super(world.loadSprite("spriteLargeAsteroid.png"), 8, world);
        setX(x);
        setY(y);
        setDirectionSpeed(180, speed);
        setTimerTime(500);
        setFrameTimer();
        this.health = 5;
        this.value = 7;
        this.world = world;
    }

    @Override
    public void update() {

    }
}
