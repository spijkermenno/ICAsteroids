package mennospijker.icasteroids;

import processing.core.PGraphics;

/**
 * The type Score counter.
 */
public class ScoreCounter extends Counter {

    /**
     * Instantiates a new Score counter.
     *
     * @param world  the world
     * @param player the player
     */
    ScoreCounter (ICAstroids world, Player player){
        super(world, player);
        setX(25);
        setY(25);
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.beginDraw();
        g.textSize(20);
        g.text(player.getPoints(), this.x, this.y);
        g.endDraw();
    }
}
