package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;

/**
 * The type Asteroid.
 */
public class Asteroid extends AnimatedSpriteObject {
    private int frame, loop;
    private final int speed = 2;

    /**
     * Instantiates a new Asteroid.
     *
     * @param world the world
     */
    Asteroid(ICAstroids world) {
        super(world.loadSprite("spriteAsteroid.png"), 8);
        setDirectionSpeed(180, speed);
    }

    @Override
    public void update() {

        if (loop == 10) {
            if (frame > 7) {
                frame = 0;
            }
            setCurrentFrameIndex(frame);
            frame++;
            loop = 0;
        } else {
            loop++;
        }
    }
}
