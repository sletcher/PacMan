package pacman.game.internal.conditions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.ICondition;

/**
 * Created by Bill on 3/14/2017.
 */
public class EdibleGhostWithin extends DecisionTreeNode implements ICondition {

  private final int distance;

  public EdibleGhostWithin(Integer dist) {
    this.distance = dist;
  }

  @Override
  public boolean test(Game game) {

    for (Constants.GHOST ghostType : Constants.GHOST.values()) {

      int ghostLoaction = game.getGhostCurrentNodeIndex(ghostType);
      int distToPac = game.getShortestPathDistance(ghostLoaction,
              game.getPacmanCurrentNodeIndex());
      if (distToPac <= distance && game.getGhostEdibleTime(ghostType) > 0) {
        return true;
      }
    }
    return false;
  }
}
