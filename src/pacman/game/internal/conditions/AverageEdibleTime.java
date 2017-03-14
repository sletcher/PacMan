package pacman.game.internal.conditions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.ICondition;

public class AverageEdibleTime extends DecisionTreeNode implements ICondition {

  private final int min;
  private final int max;

  public AverageEdibleTime(Integer min, Integer max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public boolean test(Game game) {

    float minTime = 9999;
    float maxTime = -999;
    float total = 0;
    int dist = 9999;
    Constants.GHOST closest = null;

    for (Constants.GHOST ghostType :Constants.GHOST.values()) {
      if (game.getGhostLairTime(ghostType) == 0) {
        if (game.getDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(ghostType),
                Constants.DM.PATH) < dist) {
          closest = ghostType;
        };
      }
    }

    if (game.getGhostEdibleTime(closest) > 0) {
      return true;
    }
    return false;
  }
}
