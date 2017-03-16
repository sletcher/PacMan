package pacman.game.internal.goals;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IGoal;

/**
 * Created by sletc on 3/15/2017.
 */
public class GhostsEaten implements IGoal {

    @Override
    public Boolean checkGoal(Game game) {
        for (Constants.GHOST ghostType : Constants.GHOST.values()) {
            if (game.isGhostEdible(ghostType)) {
                return false;
            }
        }
        return true;
    }
}
