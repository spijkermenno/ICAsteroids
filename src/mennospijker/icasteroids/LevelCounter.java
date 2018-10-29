package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class LevelCounter extends GameObject {
    private final ICAstroids world;
    private Player player;

    LevelCounter(ICAstroids world, Player player) {
        this.world = world;
        this.player = player;
        setX(world.screensize[0] - 100);
        setY(25);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.beginDraw();
        g.textSize(20);
        g.text( "level:" + Integer.toString((int) Math.floor(player.getPoints() / 10)), this.x, this.y);
        g.endDraw();
    }
}