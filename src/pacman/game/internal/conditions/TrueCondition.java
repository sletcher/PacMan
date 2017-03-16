package pacman.game.internal.conditions;

import pacman.game.Game;
import pacman.game.internal.ICondition;

/**
 * Created by sletc on 3/15/2017.
 */
public class TrueCondition implements ICondition {
    @Override
    public boolean test(Game game) {
        return true;
    }
}
