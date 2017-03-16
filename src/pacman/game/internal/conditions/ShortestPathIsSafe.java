package pacman.game.internal.conditions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.ICondition;

public class ShortestPathIsSafe extends DecisionTreeNode implements ICondition {

  private int nodeIndex;
  private boolean toNearestPowerPill;


  public ShortestPathIsSafe() {
    this.toNearestPowerPill = false;
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
      for (Constants.GHOST ghostType : Constants.GHOST.values()) {
        int ghostPos = game.getGhostCurrentNodeIndex(ghostType);
        int d = game.getShortestPathDistance(ghostPos, path[i]);
        if (d < i)
          return false;
      }
    }
    return true;
  }
}
