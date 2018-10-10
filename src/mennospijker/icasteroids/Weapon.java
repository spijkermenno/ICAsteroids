package mennospijker.icasteroids;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public abstract class Weapon extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    Weapon(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {

    }

    @Override
    public void update() {

    }
}
