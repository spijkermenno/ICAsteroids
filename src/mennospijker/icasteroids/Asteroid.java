package mennospijker.icasteroids;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Asteroid extends AnimatedSpriteObject {

    public Asteroid(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }

    @Override
    public void update() {

    }
}
