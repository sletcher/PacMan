package pacman.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTree;
import pacman.game.internal.IAction;
import pacman.game.internal.ICondition;
import pacman.game.internal.IDecisionTreeNode;
import pacman.game.internal.conditions.AverageEdibleTime;
import pacman.game.internal.conditions.GhostDistanceToPacman;
import pacman.game.internal.conditions.InedibleGhostWithin;
import pacman.game.internal.conditions.ShortestPathIsSafe;


public class DTPacMan extends Controller {

    private DecisionTree[] decisionTree;
    private DecisionTree startNode;



    public DTPacMan() {
        loadTree();
    }

    private void loadTree() {
        parseFile("dt.txt");
    }

    public Constants.MOVE getMove(Game game, long timeDue) {

        IAction action = startNode.makeDecision(game);
        Constants.MOVE move = action.getMove(game);

        return Constants.MOVE.DOWN;
    }

    public void parseFile(String fileName) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/ai" + System.getProperty("file.separator") + fileName)));
            String input = br.readLine();

            //preamble
            String[] pr = input.split("\t");

            int nodes = Integer.parseInt(pr[0]);
            decisionTree = new DecisionTree[nodes];

            for (int i=0; i < nodes; i++) {
                decisionTree[i] = new DecisionTree();
            }

            input = br.readLine();
            int i=0;
            while(input!=null)
            {
                String[] nd=input.split("\t");

                decisionTree[i].setCondition(newCondition(Integer.parseInt(nd[1])));
                decisionTree[i].setTrueBranch(decisionTree[Integer.parseInt(nd[2])]);
                decisionTree[i].setFalseBranch(decisionTree[Integer.parseInt(nd[3])]);

                input=br.readLine();
                i++;
            }

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ICondition newCondition(Integer distance) {
        switch (distance) {
            case 0: return new InedibleGhostWithin(distance);
            case 1: return new EdibleGhostWithin(distance);
            default: return null;
        }
    }

}
