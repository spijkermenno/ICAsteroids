package mennospijker.icasteroids;

/**
 * The type Asteroid.
 */
public class SmallAsteroid extends Asteroid {


    /**
     * Instantiates a new Asteroid.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param speed the speed
     */
    SmallAsteroid(ICAstroids world, int x, int y, int speed) {
        super(world.loadSprite("spriteSmallAsteroid.png"), 8, world);
        setX(x);
        setY(y);
        setDirectionSpeed(180, speed);
        setFrameTimer();
        this.health = 1;
        this.value = 1;
        this.world = world;
    }

    @Override
    public void update() {

    }
}
