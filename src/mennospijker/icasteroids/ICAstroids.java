package mennospijker.icasteroids;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.View;

import java.util.ArrayList;
import java.util.Random;

/* Author: Menno Spijker | Date last edit: 8 October 2018 */
/* Github Repository: https://github.com/spijkermenno/ICAsteroids */

public class ICAstroids extends GameEngine {

    private Player player;
    private int[] screensize = new int[]{850, 750};
    private static String MEDIA_URL = "src/mennospijker/icasteroids/media/";
    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private Random rand = new Random();
    private int loop = 0;


    public static void main(String[] args) {
        ICAstroids icAstroids = new ICAstroids();
        icAstroids.runSketch();
    }

    // necessary to prevent big error.
    public void settings() {
        size(screensize[0], screensize[1]);
    }

    public void setupGame() {
        frameRate(60);
        setFPSCounter(true);

        createObjects();
        createViewWithoutViewport(screensize[0], screensize[1]);
    }

    public void update() {
        int[] playerlocation = player.getLocation();

        if (asteroids.size() > 0) {

            for (Asteroid a : asteroids) {
                if (a.getCenterX() >= playerlocation[0] && a.getCenterX() <= playerlocation[1] && a.getY() == player.getY()) {
                    stop();
                }
            }

            if (asteroids.get(0).getY() > screensize[1]) {
                asteroids.remove(0);
            }
        }

        if (loop == 25) {
            Asteroid a = new Asteroid(this);
            asteroids.add(a);
            addGameObject(a, (int) (Math.random() * screensize[0] - 20 + 20), 20);
            loop = 0;
            return;
        }
        loop++;
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(0, 0, 25);
        setView(view);
    }

    private void createObjects() {
        player = new Player(this);
        addGameObject(player, ((screensize[0] - player.getWidth()) / 2), (screensize[1] - 150));
    }

    // functions to properly load sprites
    // ability to resize the sprite.
    public Sprite loadSprite(String filename, int width, int height) {
        Sprite sprite = new Sprite(this.loadImage(ICAstroids.MEDIA_URL.concat(filename)));
        sprite.resize(width, height);
        return sprite;
    }

    public Sprite loadSprite(String filename) {
        Sprite sprite = new Sprite(this.loadImage(ICAstroids.MEDIA_URL.concat(filename)));
        return sprite;
    }
}