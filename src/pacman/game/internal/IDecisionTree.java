package pacman.game.internal;

public interface IDecisionTree {

  public void setTrueBranch(IBaseNode node);

  public IBaseNode getTrueBranch();

  public void setFalseBranch(IBaseNode node);

  public IBaseNode getFalseBranch();

  public void setCondition(ICondition condition);
}
