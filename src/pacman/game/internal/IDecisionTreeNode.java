package pacman.game.internal;

import pacman.game.Game;

public interface IDecisionTreeNode {

  
  public IAction makeDecision(Game game);
}
