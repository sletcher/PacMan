package pacman.game.internal.actions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IAction;
import pacman.game.internal.IDecisionTreeNode;

/**
 * Created by Bill on 3/13/2017.
 */
public class FleeGhosts implements IAction, IDecisionTreeNode {

  @Override
  public void doAction() {

  }

  @Override
  public Constants.MOVE getMove(Game game) {

    int pacmanPosition = game.getPacmanCurrentNodeIndex();

    int closestDistance = 9999;
    Constants.GHOST closestGhost = null;
    int closestPosition = 0;

    for (Constants.GHOST ghost : Constants.GHOST.values()) {

      if (game.getGhostEdibleTime(ghost)==0 && game.getGhostLairTime(ghost)==0) {

        int dist = (int) game.getDistance(pacmanPosition, game.getGhostCurrentNodeIndex(ghost),
                Constants.DM.MANHATTAN);
        if (dist < closestDistance) {
          closestDistance = dist;
          closestGhost = ghost;
        }
      }
    }

    if (closestGhost != null) {
      closestPosition = game.getGhostCurrentNodeIndex(closestGhost);
      return game.getNextMoveAwayFromTarget(pacmanPosition, closestPosition,
              Constants.DM.PATH);
    }

    return game.getPacmanLastMoveMade();
  }

  @Override
  public IAction makeDecision(Game game) {
    return this;
  }
}
