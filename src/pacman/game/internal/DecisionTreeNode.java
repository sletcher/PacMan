package pacman.game.internal;

import pacman.game.Game;

public class DecisionTreeNode implements IBaseNode,IDecisionTree {

    private IBaseNode trueBranch;
    private IBaseNode falseBranch;
    private ICondition condition;
    private int trueNode;
    private int falseNode;

    public void setFalseNode(int n) { this.falseNode = n;}

    public int getFalseNode() {return this.falseNode;}

    public void setTrueNode(int n) {this.trueNode = n;}

    public int getTrueNode() {return this.trueNode;}

    public boolean test(Game game) {return false;}

    @Override
    public void setTrueBranch(IBaseNode node) {
        trueBranch = node;
    }

    @Override
    public IBaseNode getTrueBranch() {
        return trueBranch;
    }

    @Override
    public void setFalseBranch(IBaseNode node) {
        falseBranch = node;
    }

    @Override
    public IBaseNode getFalseBranch() {
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
