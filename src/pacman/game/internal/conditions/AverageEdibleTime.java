package pacman.game.internal.conditions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IAction;
import pacman.game.internal.ICondition;
import pacman.game.internal.IDecisionTreeNode;

/**
 * Created by Bill on 3/13/2017.
 */
public class AverageEdibleTime implements ICondition, IDecisionTreeNode {

  private int min = 1;
  private int max = 1000;


  @Override
  public boolean test(Game game) {

    float minTime = 9999;
    float maxTime = -999;
    float total = 0;

    for (Constants.GHOST ghostType :Constants.GHOST.values()) {

      float time = game.getGhostEdibleTime(ghostType);
      minTime = Math.min(time, minTime);
      maxTime = Math.max(time, maxTime);
      total+= time;
    }
    float t = maxTime;

    return (t>=min && t<max);
  }

  @Override
  public IAction makeDecision(Game game) {
    return null;
  }
}
