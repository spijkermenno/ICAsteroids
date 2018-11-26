package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Asteroid.
 */
public abstract class Asteroid extends AnimatedSpriteObject {

    private Timer timer;
    /**
     * The World.
     */
    protected ICAstroids world;
    /**
     * The Frame.
     */
    int frame, /**
     * The Timer time.
     */
    timerTime = 250, /**
     * The Health.
     */
    health, /**
     * The Value.
     */
    value;

    /**
     * Instantiates a new Asteroid.
     *
     * @param sprite      the sprite
     * @param totalFrames the total frames
     * @param world       the world
     */
    public Asteroid(Sprite sprite, int totalFrames, ICAstroids world) {
        super(sprite, totalFrames);
        this.world = world;
    }

    /**
     * Sets frame timer.
     */
    void setFrameTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (frame >= getTotalFrames()){
                    frame = 0;
                }
                setCurrentFrameIndex(frame);
                frame++;
            }
        }, timerTime, timerTime);
    }


    /**
     * Is destroyed boolean.
     *
     * @return the boolean
     */
    boolean isDestroyed() {
        return this.health <= 0;
    }

    /**
     * Hit.
     *
     * @param hitPoints the hit points
     */
    void hit(int hitPoints) {
        this.health = this.health - hitPoints;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    int getValue() {
        return this.value;
    }

    /**
     * Explode.
     */
    void explode() {
        int x = (int) this.getCenterX();
        int y = (int) this.getCenterY();
        world.addGameObject(new Explosion(world, x, y), x, y);
    }

    /**
     * Sets timer time.
     *
     * @param timerTime the timer time
     */
    void setTimerTime(int timerTime) {
        this.timerTime = timerTime;
    }
}
