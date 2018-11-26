package mennospijker.icasteroids;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/* Author: Menno Spijker | Date last edit: 28 October 2018 */
/* Github Repository: https://github.com/spijkermenno/ICAsteroids */

/**
 * The type Ic astroids.
 */
public class ICAstroids extends GameEngine {

    private Player player;
    /**
     * The Screensize.
     */
    int[] screensize = new int[]{850, 750};
    private static String MEDIA_URL = "src/mennospijker/icasteroids/media/";
    /**
     * The Sound url.
     */
    static String SOUND_URL = "src/mennospijker/icasteroids/media/sound/";
    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private Random rand = new Random();
    private int loop = 0;
    private boolean playing;
    private Timer timer, secondsTimer;
    private Dashboard informationBar;
    private int timeRun = 0;
    private Text text;
    private Sound backgroundMusic;


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
        //setFPSCounter(true);

        createObjects();
        createViewWithoutViewport(screensize[0], screensize[1]);
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    int getTime() {
        return this.timeRun;
    }

    /**
     *
     */
    private void startGameTime() {
        secondsTimer = new Timer();
        secondsTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeRun++;
            }
        }, 1000, 1000);
    }

    /**
     * Start game.
     */
    void startGame() {
        this.setAsteroidTimer(1000);
        this.playing = true;
        startGameTime();
        backgroundMusic = new Sound(this, "background_music.mp3");
    }

    /**
     * Stop game.
     */
    void stopGame() {
        this.resetGame();
        this.playing = false;
        timer.cancel();
        secondsTimer.cancel();
        timeRun = 0;
        backgroundMusic.pause();
    }

    /**
     * Gets game state.
     *
     * @return the game state
     */
    boolean getGameState() {
        return this.playing;
    }

    public void update() {
        isGamePaused();
        astroidInWorld();
        if (playing){
            text.setVisible(false);
        }else{
            text.setVisible(true);
        }
    }

    /**
     * Sets difficulty.
     */
    void setDifficulty() {
        if (player.getPoints() > 0 && player.getPoints() % 10 == 0) {
            int speed = 1000 - (player.getPoints() * 3);
            setAsteroidTimer(speed);
        }
    }

    private void setAsteroidTimer(int time) {
        if (this.getGameState()) {
            timer.cancel();
            System.out.println("timer purged.");
        }

        System.out.println(time);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                createAsteroid();
            }
        }, time, time);
    }

    private void createAsteroid() {
        int x = (int) (Math.random() * screensize[0] - 20 + 20);
        int y = 1;

        int i = rand.nextInt(100);
        Asteroid a;
        if (i <= 65) {
            a = new SmallAsteroid(this, x, y, 2);
        } else if (i >= 66 && i <= 85) {
            a = new MediumAsteroid(this, x, y, 1);
        } else if (i >= 86 && i <= 97) {
            a = new LargeAsteroid(this, x, y, 0.5f);
            System.out.println("Large Astroid");
        } else {
            a = new AlienAsteroid(this, x, y, 0.33f);
            System.out.println("Alien Astroid");
        }

        asteroids.add(a);
        addGameObject(a, x, y);
    }

    private void isGamePaused() {
        if (this.getThreadState()) {
            timer.cancel();
        }
    }

    private void astroidInWorld() {
        boolean deleteFirstAsteroid = false;

        for (Asteroid a : asteroids) {
            if (a.getY() > screensize[1]) {
                deleteFirstAsteroid = true;
            }
        }

        if (deleteFirstAsteroid) {
            asteroids.remove(0);
        }
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(loadImage(MEDIA_URL+"background_dark.png"));
        setView(view);
    }

    private void createObjects() {
        int x = 0;
        int y = (screensize[1] - 150);

        player = new Player(this, x, y);
        informationBar = new InformationBar(0, 0, screensize[0], 40, this, player);

        informationBar.setBackground(30, 30, 30);

        x = (int) ((screensize[0] - player.getWidth()) / 2);
        player.setX(x);
        player.setDefault(x, y);
        addDashboard(this.informationBar);
        addGameObject(player, x, y);
        text = new Text("Druk op spatie om te starten!", screensize[0] >> 1, screensize[1] >> 1);
        addGameObject(text);
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
    Sprite loadSprite(String filename) {
        Sprite sprite = new Sprite(this.loadImage(ICAstroids.MEDIA_URL.concat(filename)));
        return sprite;
    }

    private void resetGame() {
        player.reset();
        for (Asteroid a : asteroids) {
            deleteGameObject(a);
        }
        asteroids.clear();
    }
}
