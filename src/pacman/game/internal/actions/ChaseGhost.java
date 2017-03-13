package pacman.game.internal.actions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.GameView;
import pacman.game.internal.IAction;
import pacman.game.internal.IDecisionTreeNode;

/**
 * Created by Bill on 3/13/2017.
 */
public class ChaseGhost implements IAction, IDecisionTreeNode {

  Constants.GHOST ghost;
  boolean closest = false;

  public ChaseGhost(Constants.GHOST ghost) {

    this.ghost = ghost;
  }

  public ChaseGhost() {
    closest = true;
  }

  @Override
  public void doAction() {

  }

  @Override
  public Constants.MOVE getMove(Game game) {

    int pacmanPosition = game.getPacmanCurrentNodeIndex();
    if (closest) {
      int d = 9999;
      for (Constants.GHOST ghostType : Constants.GHOST.values()) {

        int ghostD = game.getManhattanDistance(pacmanPosition,
                game.getGhostCurrentNodeIndex(ghostType));
        if (ghostD < d) {
          d = ghostD;
          ghost = ghostType;
        }
      }
    }

    int ghostPosition = game.getGhostCurrentNodeIndex(ghost);

    return game.getNextMoveTowardsTarget(pacmanPosition, ghostPosition, Constants.DM.PATH);
  }

  @Override
  public IAction makeDecision(Game game) {
    return this;
  }
}
