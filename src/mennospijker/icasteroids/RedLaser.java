package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.GameObject;

import java.util.List;

/**
 * The type Laser.
 */
public class RedLaser extends Weapon {
    private ICAstroids world;
    private Player player;

    /**
     * Instantiates a new Laser.
     *
     * @param world  the world
     * @param player the player
     * @param x      the x
     * @param y      the y
     */
    RedLaser(ICAstroids world, Player player, int x, int y){
        super(world.loadSprite("redLaserBeam.png"), 1);
        setX(x);
        setY(y);
        this.player = player;
        this.world = world;
        setDirectionSpeed(0, 5);
        sound(world);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {
        for (GameObject g : list){
            if (g instanceof Asteroid) {
                Asteroid asteroid = (Asteroid) g;

                world.deleteGameObject(this);
                asteroid.hit(1);
                new Sound(world, "hitmarker.mp3");

                if (asteroid.isDestroyed()) {
                    player.addPoints(asteroid.getValue());
                    if (asteroid instanceof AlienAsteroid){
                        player.setLaserType("blue");
                    }
                    asteroid.explode();
                    world.deleteGameObject(g);
                    player.removeLaserBeam(this);
                }
                world.setDifficulty();

            }
        }
    }
}
