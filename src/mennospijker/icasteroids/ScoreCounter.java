package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class ScoreCounter extends GameObject {
    private Player player;
    private ICAstroids world;

    ScoreCounter (ICAstroids world, Player player){
        this.player = player;
        this.world = world;
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
