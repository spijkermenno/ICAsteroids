package mennospijker.icasteroids;

import nl.han.ica.oopg.dashboard.Dashboard;

/**
 * The type Information bar.
 */
public class InformationBar extends Dashboard {
    /**
     * Instantiates a new Information bar.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param world  the world
     * @param player the player
     */
    public InformationBar(float x, float y, float width, float height, ICAstroids world, Player player) {
        super(x, y, width, height);


        ScoreCounter scorecounter = new ScoreCounter(world, player);
        TimeCounter timecounter = new TimeCounter(world, player);
        LevelCounter levelcounter = new LevelCounter(world, player);

        addGameObject(scorecounter);
        addGameObject(timecounter);
        addGameObject(levelcounter);
    }
}
