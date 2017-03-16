package pacman.game.internal;

import pacman.game.Constants.MOVE;
import pacman.game.Game;


public interface IAction {
  enum ACTION {
    CHASE, FLEE, PILL, PPILL
  }

  MOVE getMove(Game game);
}
