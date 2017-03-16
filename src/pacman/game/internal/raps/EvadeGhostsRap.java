package pacman.game.internal.raps;

import pacman.controllers.RAPPacMan;
import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IAction;
import pacman.game.internal.IRap;
import pacman.game.internal.Rap;
import pacman.game.internal.actions.FleeGhosts;

import java.util.ArrayList;

public class EvadeGhostsRap implements IRap {
    final Integer distance;
    final Integer edibleTime;
    final IAction action;
    final IRap.type type =IRap.type.RAP;
    final IRap[] taskNet;

    public EvadeGhostsRap(Game game, IAction action, Integer distance, Integer edibleTime, IRap[] taskNet) {
        this.distance = distance;
        this.edibleTime = edibleTime;
        this.action = action;
        this.taskNet = taskNet;
    }

    @Override
    public Boolean checkGoal(Game game) {
        return false;
    }

    @Override
    public Boolean checkValidity(Game game) {
        for (Constants.GHOST ghostType : Constants.GHOST.values()) {
            int ghostLoaction = game.getGhostCurrentNodeIndex(ghostType);
            int distToPac = game.getShortestPathDistance(ghostLoaction,
                    game.getPacmanCurrentNodeIndex());
            if (distToPac <= distance && game.getGhostEdibleTime(ghostType) > edibleTime) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean handleRap(Game game) {

    }
}
