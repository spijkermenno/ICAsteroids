package mennospijker.icasteroids;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/* Author: Menno Spijker | Date last edit: 8 October 2018 */
/* Github Repository: https://github.com/spijkermenno/ICAsteroids */

/**
 * The type Ic astroids.
 */
public class ICAstroids extends GameEngine {

    private Player player;
    private int[] screensize = new int[]{850, 750};
    private static String MEDIA_URL = "src/mennospijker/icasteroids/media/";
    /**
     * The Asteroids.
     */
    public ArrayList<Asteroid> asteroids = new ArrayList<>();
    private Random rand = new Random();
    private int loop = 0;
    /**
     * The Timer.
     */
    public Timer timer;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ICAstroids icAstroids = new ICAstroids();
        icAstroids.runSketch();
    }

    public void settings() {
        size(screensize[0], screensize[1]);
    }

    public void setupGame() {
        frameRate(30);
        setFPSCounter(true);

        createObjects();
        createViewWithoutViewport(screensize[0], screensize[1]);
        setAsteroidTimer();
    }

    private void setAsteroidTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                createAsteroid();
            }
        }, 1000, 1000);
    }

    private void createAsteroid() {
        int x = (int) (Math.random() * screensize[0] - 20 + 20);
        int y = 1;
        Asteroid a = new SmallAsteroid(this, x, y);
        asteroids.add(a);
        addGameObject(a, x, y);
    }


    public void update() {
        if (this.getThreadState()) {
            timer.cancel();
        }

        boolean deleteFirstAsteroid = false;

        for (Asteroid a : asteroids) {
            if (a.getY() > screensize[1]) {
                deleteFirstAsteroid = true;
            }
        }

        if (deleteFirstAsteroid){
            asteroids.remove(0);
        }

    }


    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(0, 0, 25);
        setView(view);
    }

    private void createObjects() {
        int x = (int) 0;
        int y = (screensize[1] - 150);

        player = new Player(this, x, y);
        x = (int) ((screensize[0] - player.getWidth()) / 2);
        player.setX(x);
        player.setDefault(x, y);
        addGameObject(player, x, y);
    }

    /**
     * Load sprite sprite.
     *
     * @param filename the filename
     * @param width    the width
     * @param height   the height
     * @return the sprite
     */
    public Sprite loadSprite(String filename, int width, int height) {
        Sprite sprite = new Sprite(this.loadImage(ICAstroids.MEDIA_URL.concat(filename)));
        sprite.resize(width, height);
        return sprite;
    }

    /**
     * Load sprite sprite.
     *
     * @param filename the filename
     * @return the sprite
     */
    public Sprite loadSprite(String filename) {
        Sprite sprite = new Sprite(this.loadImage(ICAstroids.MEDIA_URL.concat(filename)));
        return sprite;
    }


    public void resetGame() {
        for (Asteroid a : asteroids) {
            deleteGameObject(a);
        }
        asteroids.clear();
        player.reset();
    }
}