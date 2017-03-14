package pacman.game.internal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pacman.game.Game;

public class DecisionTreeNode implements IDecisionTreeNode,IDecisionTree {

    private IDecisionTreeNode trueBranch;
    private IDecisionTreeNode falseBranch;
    private ICondition condition;
    private int trueNode;
    private int falseNode;

    public void setFalseNode(int n) { this.falseNode = n;}

    public int getFalseNode() {return this.falseNode;}

    public void setTrueNode(int n) {this.trueNode = n;}

    public int getTrueNode() {return this.trueNode;}

    public boolean test(Game game) {return false;}

    @Override
    public void setTrueBranch(IDecisionTreeNode node) {
        trueBranch = node;
    }

    @Override
    public IDecisionTreeNode getTrueBranch() {
        return trueBranch;
    }

    @Override
    public void setFalseBranch(IDecisionTreeNode node) {
        falseBranch = node;
    }

    @Override
    public IDecisionTreeNode getFalseBranch() {
        return falseBranch;
    }

    @Override
    public void setCondition(ICondition condition) {
        this.condition = condition;
    }

    @Override
    public IAction makeDecision(Game game) {

        if (test(game)) {
            return trueBranch.makeDecision(game);
        }
        else
            return falseBranch.makeDecision(game);
    }
}
