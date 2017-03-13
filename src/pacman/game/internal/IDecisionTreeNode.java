package pacman.game.internal;

import pacman.game.Game;

/**
 * Created by Bill on 3/13/2017.
 */
public interface IDecisionTreeNode {

  
  public IAction makeDecision(Game game);
}
