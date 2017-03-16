package pacman.game.internal.goals;

import pacman.game.Game;
import pacman.game.internal.IGoal;

/**
 * Created by sletc on 3/15/2017.
 */
public class AllPowerPillsEaten implements IGoal {

    @Override
    public Boolean checkGoal(Game game) {
        return (game.getNumberOfActivePowerPills() == 0);
    }
}
