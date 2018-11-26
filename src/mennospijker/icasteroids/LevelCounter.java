package mennospijker.icasteroids;

import processing.core.PGraphics;

/**
 * The type Level counter.
 */
public class LevelCounter extends Counter {

    /**
     * Instantiates a new Level counter.
     *
     * @param world  the world
     * @param player the player
     */
    LevelCounter(ICAstroids world, Player player) {
        super(world, player);
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
        g.text( "level:" + Integer.toString( this.player.getPoints() / 10), this.x, this.y);
        g.endDraw();
    }
}