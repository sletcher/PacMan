package pacman.game.internal.actions;

import java.util.ArrayList;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IAction;
import pacman.game.internal.IDecisionTreeNode;

/**
 * Created by Bill on 3/13/2017.
 */
public class GoNearestPowerPill implements IAction, IDecisionTreeNode {


  @Override
  public void doAction() {

  }

  private int _findNearestPill(Game game, int current, boolean includeNormalPills,
                               boolean includePowerPills) {

    ArrayList<Integer> targets = new ArrayList<Integer>();

    if (includeNormalPills) {
      int[] pills = game.getActivePillsIndices();
      for (int i=0; i<pills.length; i++) {
        if (game.isPillStillAvailable(i))
          targets.add(pills[i]);
      }
    }

    if (includePowerPills) {
      int[] powerPills = game.getPowerPillIndices();
      for (int i=0; i<powerPills.length; i++) {
        if (game.isPowerPillStillAvailable(i))
          targets.add(powerPills[i]);
      }
    }

    int[] targetsArray = new int[targets.size()];

    for (int i=0; i<targetsArray.length; i++) {
      targetsArray[i] = targets.get(i);
    }

    return game.getClosestNodeIndexFromNodeIndex(current, targetsArray, Constants.DM.PATH);
  }

  private int findNearestAnyPill(Game game, int current) {

    return _findNearestPill(game, current, true, true);
  }

  @Override
  public Constants.MOVE getMove(Game game) {

    int pacmanPosition = game.getPacmanCurrentNodeIndex();
    int nearestPillPosition = findNearestAnyPill(game, pacmanPosition);

    return game.getNextMoveTowardsTarget(pacmanPosition, nearestPillPosition, Constants.DM.PATH);
  }

  @Override
  public IAction makeDecision(Game game) {
    return this;
  }
}
