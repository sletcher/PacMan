package pacman.game.internal.goals;

import pacman.game.Game;
import pacman.game.internal.IGoal;

/**
 * Created by sletc on 3/15/2017.
 */
public class AllPillsEaten implements IGoal{
    @Override
    public Boolean checkGoal(Game game) {
        return (game.getNumberOfActivePills() > 0);
    }
}
