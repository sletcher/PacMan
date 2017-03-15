package pacman.game.internal.PrimitiveRaps;

import pacman.game.Game;
import pacman.game.internal.IRap;
import pacman.game.internal.actions.FleeGhosts;

/**
 * Created by sam on 3/15/17.
 */
public class rFleeGhosts extends FleeGhosts implements IRap {

    @Override
    public Boolean checkGoal(Game game) {
        return false;
    }

    @Override
    public Boolean checkValidity(Game game) {
        return true;
    }

    @Override
    public Object[] handleRap(Game game) {
        return new Object[0];
    }
}
