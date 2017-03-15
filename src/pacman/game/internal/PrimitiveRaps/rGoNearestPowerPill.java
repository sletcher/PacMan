package pacman.game.internal.PrimitiveRaps;

import pacman.game.Game;
import pacman.game.internal.IRap;
import pacman.game.internal.actions.GoNearestPowerPill;

/**
 * Created by sam on 3/15/17.
 */
public class rGoNearestPowerPill extends GoNearestPowerPill implements IRap {
    @Override
    public Boolean checkGoal(Game game) {
        return false;
    }

    @Override
    public Boolean checkValidity(Game game) {
        return false;
    }

    @Override
    public Object[] handleRap(Game game) {
        return new Object[0];
    }
}
