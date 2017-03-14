package pacman.game.internal;

public interface IDecisionTree {

  public void setTrueBranch(IDecisionTreeNode node);

  public IDecisionTreeNode getTrueBranch();

  public void setFalseBranch(IDecisionTreeNode node);

  public IDecisionTreeNode getFalseBranch();

  public void setCondition(ICondition condition);
}
