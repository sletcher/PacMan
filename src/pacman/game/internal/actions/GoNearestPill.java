package pacman.game.internal.actions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IAction;
import pacman.game.internal.IDecisionTreeNode;

/**
 * Created by Bill on 3/13/2017.
 */
public class GoNearestPill implements IAction, IDecisionTreeNode {


  @Override
  public void doAction() {

  }

  @Override
  public Constants.MOVE getMove(Game game) {

    int pacIndex = game.getPacmanCurrentNodeIndex();
    int pillIndex = -1;

    int[] pills = game.getActivePillsIndices();
    int d = 9999;
    for (int i=0; i<pills.length; i++) {

      int pillD = game.getShortestPathDistance(pacIndex, pills[i]);
      if (pillD < d) {
        d = pillD;
        pillIndex = pills[i];
      }
    }

    if (pillIndex < 0) {
      return game.getPacmanLastMoveMade();
    }

    Constants.MOVE move = game.getNextMoveTowardsTarget(pacIndex, pillIndex, Constants.DM.PATH);

    return move;
  }

  @Override
  public IAction makeDecision(Game game) {
    return this;
  }
}
