package pacman.game.internal;

/**
 * Created by Bill on 3/13/2017.
 */
public interface IDecisionTree {

  public void setTrueBranch(IDecisionTreeNode node);

  public IDecisionTreeNode getTrueBranch();

  public void setFalseBranch(IDecisionTreeNode node);

  public IDecisionTreeNode getFalseBranch();

  public void setCondition(ICondition condition);
}
