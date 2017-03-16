package pacman.game.internal.actions;

import java.util.ArrayList;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.IAction;

/**
 * Created by Bill on 3/13/2017.
 */
public class GoNearestPowerPill extends DecisionTreeNode implements IAction {



  private int _findNearestPill(Game game, int current, boolean includeNormalPills,
                               boolean includePowerPills) {

    ArrayList<Integer> targets = new ArrayList<>();

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

    if (nearestPillPosition < 0) {
      nearestPillPosition = 0;
    }

    return game.getNextMoveTowardsTarget(pacmanPosition, nearestPillPosition, Constants.DM.PATH);
  }

  @Override
  public IAction makeDecision(Game game) {
    return this;
  }
}
