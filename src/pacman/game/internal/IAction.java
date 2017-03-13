package pacman.game.internal;

import pacman.game.Constants.MOVE;
import pacman.game.Game;

/**
 * Created by Bill on 3/11/2017.
 */
public interface IAction {

  /**
   * Performs the action associated with this action node.
   */
  public void doAction();

  public MOVE getMove(Game game);
}
