package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.GameObject;

import java.util.List;

/**
 * The type Laser.
 */
public class Laser extends Weapon {
    private ICAstroids world;
    private Player player;

    /**
     * Instantiates a new Laser.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     */
    public Laser(ICAstroids world, Player player, int x, int y){
        super(world.loadSprite("redLaserBeam.png"), 1);
        setX(x);
        setY(y);

        this.world = world;
        setDirectionSpeed(0, 5);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {
        for (GameObject g : list){
            if (g instanceof Asteroid){

                float x = this.getCenterX();
                float y = this.getCenterY();

                world.deleteGameObject(g);
                world.deleteGameObject(this);
            }
        }
    }

    @Override
    public void update() {
    }
}
