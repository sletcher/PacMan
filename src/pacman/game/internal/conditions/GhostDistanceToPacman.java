package pacman.game.internal.conditions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IAction;
import pacman.game.internal.ICondition;
import pacman.game.internal.IDecisionTreeNode;

public class GhostDistanceToPacman implements ICondition, IDecisionTreeNode {

  private int min = 0;
  private int max = 200;

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

  @Override
  public IAction makeDecision(Game game) {
    return null;
  }
}
