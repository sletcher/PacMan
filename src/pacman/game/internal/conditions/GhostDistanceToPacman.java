package pacman.game.internal.conditions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.ICondition;

public class GhostDistanceToPacman extends DecisionTreeNode implements ICondition {

  private final int min;
  private final int max;

  public GhostDistanceToPacman(Integer min, Integer max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public boolean test(Game game) {

    int pacPosition = game.getPacmanCurrentNodeIndex();
    for (Constants.GHOST ghostType : Constants.GHOST.values()) {

      int ghostLoaction = game.getGhostCurrentNodeIndex(ghostType);
      int distToPac = game.getShortestPathDistance(ghostLoaction, pacPosition);
      if (distToPac >= min && distToPac < max) {
        return true;
      }
    }
    return false;
  }

}
