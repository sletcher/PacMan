package pacman.controllers;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.Constants.MOVE;

/**
 * Created by Bill on 3/10/2017.
 */
public class RAPPacMan extends Controller<MOVE> {


  @Override
  public MOVE getMove(Game game, long timeDue) {
    return MOVE.LEFT;
  }
}
