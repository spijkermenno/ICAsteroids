package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class TimeCounter extends GameObject {
    private final ICAstroids world;
    private Player player;

    TimeCounter(ICAstroids world, Player player) {
        this.player = player;
        this.world = world;
        setX((world.screensize[0] / 2) - 25);
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

    public String calculateTime(int time){
        int minutes = 0;
        int seconds = 0;

        while( time >= 60){
            minutes++;
            time = time - 60;
        }

        if (time < 60){
            seconds = time;
        }

        return Integer.toString(minutes) + ":" + Integer.toString(seconds);
    }
}
