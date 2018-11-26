package mennospijker.icasteroids;

import processing.core.PGraphics;

/**
 * The type Time counter.
 */
public class TimeCounter extends Counter {

    /**
     * Instantiates a new Time counter.
     *
     * @param world  the world
     * @param player the player
     */
    TimeCounter(ICAstroids world, Player player) {
        super(world, player);
        setX((world.screensize[0] >> 1) - 33);
        setY(25);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.beginDraw();
        g.textSize(20);
        g.text(calculateTime(world.getTime()), this.x, this.y);
        g.endDraw();
    }

    private String calculateTime(int time) {
        int minutes = 0;
        int seconds;

        while (time >= 60) {
            minutes++;
            time = time - 60;
        }

        seconds = time;
        String timeString = "";

        if (minutes < 10) {
            timeString += "0".concat(Integer.toString(minutes));
        } else {
            timeString += minutes;
        }

        timeString += ":";

        if (seconds < 10) {
            timeString += "0".concat(Integer.toString(seconds));
        } else {
            timeString += seconds;
        }



        return timeString;
    }
}
