package pacman.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.IAction;
import pacman.game.internal.actions.ChaseGhost;
import pacman.game.internal.actions.FleeGhosts;
import pacman.game.internal.actions.GoNearestPill;
import pacman.game.internal.actions.GoNearestPowerPill;
import pacman.game.internal.conditions.AverageEdibleTime;
import pacman.game.internal.conditions.GhostDistanceToPacman;
import pacman.game.internal.conditions.InedibleGhostWithin;
import pacman.game.internal.conditions.ShortestPathIsSafe;


public class DTPacMan extends Controller {

    private DecisionTreeNode[] decisionTreeNode;
    private DecisionTreeNode startNode;




    public DTPacMan() {
        loadTree();
    }

    private void loadTree() {
        parseFile("dt.txt");
    }

    public Constants.MOVE getMove(Game game, long timeDue) {

        IAction action = decisionTreeNode[0].makeDecision(game);
        Constants.MOVE move = action.getMove(game);

        return move;
    }

    public void parseFile(String fileName) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/ai" + System.getProperty("file.separator") + fileName)));
            String input = br.readLine();

            //preamble
            String[] pr = input.split("\t");

            int nodes = Integer.parseInt(pr[0]);
            decisionTreeNode = new DecisionTreeNode[nodes];

            for (int i=0; i < nodes; i++) {
                decisionTreeNode[i] = new DecisionTreeNode();
            }

            input = br.readLine();
            int i=0;
            while(input!=null)
            {
                String[] nd=input.split("\t");

                decisionTreeNode[i] = newCondition(Integer.parseInt(nd[1]), Integer.parseInt(nd[4]),
                        Integer.parseInt(nd[5]));
                //Set true node
                decisionTreeNode[i].setTrueNode(Integer.parseInt(nd[2]));
                //Set false node
                decisionTreeNode[i].setFalseNode(Integer.parseInt(nd[3]));



                input=br.readLine();
                i++;
            }

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        for (int i =0; i < decisionTreeNode.length; i++) {

            if (decisionTreeNode[i].getTrueNode() < 0 || decisionTreeNode[i].getTrueNode() > decisionTreeNode.length) {
                decisionTreeNode[i].setTrueBranch(null);
            }
            else {
                decisionTreeNode[i].setTrueBranch(decisionTreeNode[decisionTreeNode[i].getTrueNode()]);
            }

            if (decisionTreeNode[i].getFalseNode() < 0 || decisionTreeNode[i].getFalseNode() > decisionTreeNode.length) {
                decisionTreeNode[i].setFalseBranch(null);
            }
            else {
                decisionTreeNode[i].setFalseBranch(decisionTreeNode[decisionTreeNode[i].getFalseNode()]);
            }
        }
    }


    public DecisionTreeNode newCondition(Integer n, Integer var1, Integer var2) {
        switch (n) {
            case 0: return new InedibleGhostWithin(var1);
            case 1: return new AverageEdibleTime(var1, var2);
            case 2: return new GhostDistanceToPacman(var1, var2);
            case 3: return new ShortestPathIsSafe();

            case 10: return new ChaseGhost();
            case 11: return new FleeGhosts();
            case 12: return new GoNearestPill();
            case 13: return new GoNearestPowerPill();
            //case 14: return new ReduceThreat();

            default: return new GoNearestPill();
        }
    }

}
