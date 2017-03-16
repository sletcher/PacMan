package pacman.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.omg.CORBA.Object;

import pacman.Executor;
import pacman.controllers.DTPacMan;
import pacman.controllers.examples.StarterGhosts;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.actions.FleeGhosts;
import pacman.game.internal.actions.GoNearestPill;
import pacman.game.internal.conditions.GhostDistanceToPacman;


/**
 * Created by Bill on 3/15/2017.
 */
public class DecisionTreeTests {

  public void writeTestFile() {

    //write test file
  }

  @Test
  public void testRunGame() {

    Executor exec = new Executor();
    exec.runExperiment(new DTPacMan(), new StarterGhosts(), 100);

    assertTrue(true);
  }

  @Test
  public void testLoadFile() {

    DTPacMan dtPac = new DTPacMan("test.txt");
    DecisionTreeNode[] tree = dtPac.getDecisionTreeNode();

    assertTrue(tree[0] instanceof GhostDistanceToPacman);
    assertTrue(tree[1] instanceof FleeGhosts);
    assertTrue(tree[2] instanceof GoNearestPill);
  }
}
