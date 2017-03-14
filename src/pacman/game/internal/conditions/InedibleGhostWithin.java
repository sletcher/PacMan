package pacman.game.internal.conditions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.ICondition;


public class InedibleGhostWithin extends DecisionTreeNode implements ICondition {

    public final Integer distance;

    public InedibleGhostWithin(Integer distance) {
        this.distance = distance;
    }


    @Override
    public boolean test(Game game) {
        for (Constants.GHOST ghostType : Constants.GHOST.values()) {

            int ghostLoaction = game.getGhostCurrentNodeIndex(ghostType);
            int distToPac = game.getShortestPathDistance(ghostLoaction,
                    game.getPacmanCurrentNodeIndex());
            if (distToPac <= distance && game.getGhostLairTime(ghostType) == 0 &&
                    game.getGhostEdibleTime(ghostType)== 0) {
                return true;
            }
        }
        return false;
    }
}
