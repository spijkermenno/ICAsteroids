import javazoom.jl.player.Player;
import nl.han.ica.oopg.engine.GameEngine;
import processing.core.PApplet;

/* Author: Menno Spijker | Date last edit: 8 October 2018 */

public class ICAstroids extends GameEngine {

    private Player player;

    public static void main(String[] args) {
        String[] processingArgs = {"ICAstroids"};
        ICAstroids mySketch = new ICAstroids();

        PApplet.runSketch(processingArgs, mySketch);
    }

    public void setupGame() {
        int worldWidth  = 0;
        int worldHeight = 0;
    }

    public void update() {

    }
}