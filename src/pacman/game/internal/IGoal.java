package pacman.game.internal;

import pacman.game.Game;

/**
 * Created by sletc on 3/15/2017.
 */
public interface IGoal {
    enum GOAL {
        GHOSTSEATEN, PILLSEATEN, PPILLSEATEN, TRUE
    }

    /**
     * Checks the success of the RAPs goal
     * @return true, if the goal succeeded
     */
    Boolean checkGoal(Game game);
}
