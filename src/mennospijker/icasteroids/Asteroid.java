package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Asteroid extends AnimatedSpriteObject {

    private Timer timer;
    protected ICAstroids world;
    int frame, timerTime = 250, health, value;

    public Asteroid(Sprite sprite, int totalFrames, ICAstroids world) {
        super(sprite, totalFrames);
        this.world = world;
    }

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


    boolean isDestroyed() {
        return this.health <= 0;
    }

    void hit() {
        this.health--;
    }

    int getValue() {
        return this.value;
    }

    void explode() {
        int x = (int) this.getCenterX();
        int y = (int) this.getCenterY();
        world.addGameObject(new Explosion(world, x, y), x, y);
    }

    void setTimerTime(int timerTime) {
        this.timerTime = timerTime;
    }
}
