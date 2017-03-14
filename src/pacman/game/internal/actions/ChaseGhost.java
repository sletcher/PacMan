package pacman.game.internal.actions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.IAction;

public class ChaseGhost extends DecisionTreeNode implements IAction  {

  Constants.GHOST ghost;
  boolean closest = false;

  public ChaseGhost(Constants.GHOST ghost) {

    this.ghost = ghost;
  }

  public ChaseGhost() {
    closest = true;
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
