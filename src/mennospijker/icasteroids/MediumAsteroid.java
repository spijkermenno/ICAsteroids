package mennospijker.icasteroids;

/**
 * The type Asteroid.
 */
public class MediumAsteroid extends Asteroid {


    /**
     * Instantiates a new Asteroid.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param speed the speed
     */
    MediumAsteroid(ICAstroids world, int x, int y, int speed) {
        super(world.loadSprite("spriteMediumAstroid.png"), 8, world);
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
