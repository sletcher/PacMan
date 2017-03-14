package pacman.game.internal;

import pacman.game.Constants.MOVE;
import pacman.game.Game;


public interface IAction {

  enum Actions {
    CHASE, FLEE, PILL, POWERPILL, REDUCE
  }

  public MOVE getMove(Game game);
}
