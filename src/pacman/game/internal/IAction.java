package pacman.game.internal;

import pacman.game.Constants.MOVE;
import pacman.game.Game;


public interface IAction {
  MOVE getMove(Game game);
}
