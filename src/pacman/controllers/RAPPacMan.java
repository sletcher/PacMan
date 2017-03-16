package pacman.controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.Constants.MOVE;
import pacman.game.internal.*;
import pacman.game.internal.actions.ChaseGhost;
import pacman.game.internal.actions.FleeGhosts;
import pacman.game.internal.actions.GoNearestPill;
import pacman.game.internal.actions.GoNearestPowerPill;
import pacman.game.internal.conditions.EdibleGhostWithin;
import pacman.game.internal.conditions.InedibleGhostWithin;
import pacman.game.internal.conditions.TrueCondition;
import pacman.game.internal.goals.AllPillsEaten;
import pacman.game.internal.goals.AllPowerPillsEaten;
import pacman.game.internal.goals.GhostsEaten;
import pacman.game.internal.goals.TrueGoal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;

public class RAPPacMan extends Controller {
  public LinkedList<Rap> raps = new LinkedList<>();
  ArrayList<String[]> taskArray = new ArrayList<>();


  enum TYPE {
    RAP, TASK
  }
  public RAPPacMan() {
    parseFile("rap1.txt");
  }

  public void parseFile(String fileName) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/ai" + System.getProperty("file.separator") + fileName)));
      String input = br.readLine();

      while (input != null) {
        String[] nd = input.split("\t");

        if (TYPE.RAP.toString().equals(nd[1])) {
          raps.add(parseRap(nd));
        } else if(TYPE.TASK.toString().equals(nd[1])) {
          taskArray.add(nd);
        }

        input = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public MOVE getMove(Game game, long timeDue) {
    LinkedList<Rap> craps = new LinkedList<>(raps);
    Rap rap = craps.pop();
    MOVE output = null;

    while (craps.size() > 0) {
      output = rap.handleRap(game, craps);
      if (output != null) {
        break;
      }
      rap = craps.pop();
    }

    return output;
  }

  IGoal goalFactory(IGoal.GOAL goal) {
    switch (goal) {
      case GHOSTSEATEN: return new GhostsEaten();
      case PILLSEATEN: return new AllPillsEaten();
      case PPILLSEATEN: return new AllPowerPillsEaten();
      default: return new TrueGoal();
    }
  }

  ICondition conditionFactory(ICondition.CONDITION condition, String var1) {
    switch (condition) {
      case EDIBLEGHOST: return new EdibleGhostWithin(Integer.valueOf(var1));
      case INEDIBLEGHOST: return new InedibleGhostWithin(Integer.valueOf(var1));
      default: return new TrueCondition();
    }
  }

  IAction actionFactory(IAction.ACTION action) {
    switch (action) {
      case FLEE: return new FleeGhosts();
      case CHASE: return new ChaseGhost();
      case PPILL: return new GoNearestPowerPill();
      default: return new GoNearestPill();
    }
  }

  Rap parseRap(String[] nd) {
    ArrayList<ICondition> conditions = new ArrayList<>();
    ArrayList<IGoal> goals = new ArrayList<>();
    ArrayList<Rap> taskNet = new ArrayList<>();

    String[] goalStrings = nd[3].split(",");
    String[] conditionStrings = nd[4].split(";");
    String[] taskNetStrings = nd[5].split(",");

    for (String goalString : goalStrings) {
      goals.add(goalFactory(IGoal.GOAL.valueOf(goalString)));
    }
    IGoal[] g = goals.toArray(new IGoal[goals.size()]);

    for (String s : conditionStrings) {
      String[] c = s.split(",");
      ICondition cn = conditionFactory(ICondition.CONDITION.valueOf(c[0]), c[1]);
      conditions.add(cn);
    }
    ICondition[] c = conditions.toArray(new ICondition[conditions.size()]);

    for (String s : taskNetStrings) {
      taskNet.add(parseRapTask(taskArray.get(Integer.valueOf(s))));
    }
    Rap[] r = taskNet.toArray(new Rap[taskNet.size()]);

    return new Rap(nd[1], g, c, r);
  }

  Rap parseRapTask(String[] nd) {
    ArrayList<ICondition> conditions = new ArrayList<>();
    ArrayList<IGoal> goals = new ArrayList<>();
    ArrayList<Rap> taskNet = new ArrayList<>();

    String[] goalStrings = nd[3].split(",");
    String[] conditionStrings = nd[4].split(";");

    for (String goalString : goalStrings) {
      goals.add(goalFactory(IGoal.GOAL.valueOf(goalString)));
    }
    IGoal[] g = goals.toArray(new IGoal[goals.size()]);

    for (String s : conditionStrings) {
      String[] c = s.split(",");
      if (c.length > 1 ) {
        conditions.add(conditionFactory(ICondition.CONDITION.valueOf(c[0]), c[1]));
      } else {
        conditions.add(conditionFactory(ICondition.CONDITION.valueOf(c[0]), "0"));
      }
    }
    ICondition[] c = conditions.toArray(new ICondition[conditions.size()]);

    IAction action = actionFactory(IAction.ACTION.valueOf(nd[5]));

    return new Rap(nd[1], g, c, action);
  }
}
