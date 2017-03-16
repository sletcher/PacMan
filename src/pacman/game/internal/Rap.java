package pacman.game.internal;

import pacman.controllers.RAPPacMan;
import pacman.game.Constants;
import pacman.game.Game;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sam on 3/15/17.
 */
public class Rap {
    String name;
    IGoal[] goals;
    ICondition[] conditions;
    IAction action;
    Rap[] taskNet;

    public Rap(String name, IGoal[] goals, ICondition[] conditions, Rap[] taskNet) {
        this.name = name;
        this.goals = goals;
        this.conditions = conditions;
        this.taskNet = taskNet;
    }

    public Rap(String name, IGoal[] goals, ICondition[] conditions, IAction action) {
        this.name = name;
        this.goals = goals;
        this.conditions = conditions;
        this.action = action;
    }

    Boolean checkGoal(Game game) {
        for (int i = 0; i < goals.length; i++) {
            if (!goals[i].checkGoal(game)) {
                return false;
            }
        }
        return true;
    }

    Boolean checkValidity(Game game) {
        for (ICondition condition : conditions) {
            if (!condition.test(game)) {
                return false;
            }
        }
        return true;
    }

    // Returns true if this is a RapTask. Only
    public Constants.MOVE handleRap(Game game, LinkedList<Rap> raps) {
        if (checkGoal(game)) { return null; }

        raps.add(this);
        if (!checkValidity(game)) { return null; }

        if (action != null) { return action.getMove(game); }

        for (Rap rapTask : taskNet) {
            raps.add(0, rapTask);
        }

        return null;
    }
}
