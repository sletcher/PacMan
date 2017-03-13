package pacman.game.internal.conditions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IAction;
import pacman.game.internal.ICondition;
import pacman.game.internal.IDecisionTreeNode;

/**
 * Created by Bill on 3/13/2017.
 */
public class ShortestPathIsSafe implements ICondition, IDecisionTreeNode {

  private int nodeIndex;
  private boolean toNearestPowerPill;

  public ShortestPathIsSafe(int nodeIndex) {
    this.nodeIndex = nodeIndex;
  }

  public ShortestPathIsSafe() {
    this.toNearestPowerPill = true;
  }

  @Override
  public boolean test(Game game) {

    int pacIndex = game.getPacmanCurrentNodeIndex();

    if (toNearestPowerPill) {
      nodeIndex = pacIndex;
      int[] pills = game.getActivePowerPillsIndices();
      int d = 9999;
      for (int i = 0; i<pills.length; i++) {
        int pillD = game.getShortestPathDistance(pacIndex,pills[i]);
        if (pillD < d) {
          d = pillD;
          nodeIndex = pills[i];
        }
      }
    }

    int[] path = game.getShortestPath(pacIndex, nodeIndex);

    for (int i = 0; i < path.length; i++) {
      boolean isSafe = true;
      for (Constants.GHOST ghostType : Constants.GHOST.values()) {
        int ghostPos = game.getGhostCurrentNodeIndex(ghostType);
        int d = game.getShortestPathDistance(ghostPos, path[i]);
        if (d < i)
          isSafe = false;
      }
      if (!isSafe)
        return false;
    }
    return true;
  }

  @Override
  public IAction makeDecision(Game game) {
    return null;
  }
}
