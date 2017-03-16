package pacman.game.internal;

import pacman.game.Game;

public interface IBaseNode {

  public IAction makeDecision(Game game);
}
