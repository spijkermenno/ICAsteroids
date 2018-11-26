package mennospijker.icasteroids;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

/**
 * The type Weapon.
 */
public abstract class Weapon extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    /**
     * Instantiates a new Weapon.
     *
     * @param sprite      the sprite
     * @param totalFrames the total frames
     */
    Weapon(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {

    }

    @Override
    public void update() {

    }

    /**
     * Sound.
     *
     * @param world the world
     */
    public void sound(ICAstroids world) {
        Sound sound = new Sound(world, "laser_decreased.mp3");
    }
}
