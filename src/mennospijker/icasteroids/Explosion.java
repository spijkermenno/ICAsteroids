package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Explosion.
 */
public class Explosion extends AnimatedSpriteObject {

    /**
     * The World.
     */
    ICAstroids world;
    private Timer timer;
    private int frame, loop = 0;

    /**
     * Instantiates a new Explosion.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     */
    Explosion(ICAstroids world, int x, int y) {
        super(world.loadSprite("spriteExplosion.png"), 5);
        setX(x);
        setY(y);
        this.world = world;
        setFrameTimer();
        Sound sound = new Sound(world, "explosion.mp3");
    }

    @Override
    public void update() {

    }

    private void setFrameTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (frame >= getTotalFrames()) {
                    if (loop > 3) {
                        deleteSelf();
                    }else {
                        frame = getTotalFrames() - 2;
                    }
                    loop++;
                }
                setCurrentFrameIndex(frame);
                frame++;
            }
        }, 133, 133);
    }

    /**
     * Delete self.
     */
    public void deleteSelf(){
        world.deleteGameObject(this);
    }
}
