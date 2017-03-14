package pacman.game.internal.actions;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.IAction;
import pacman.game.internal.IDecisionTreeNode;

public class ReduceThreat implements IAction, IDecisionTreeNode {

  private float[] threatMap;

  public ReduceThreat(float[] threatMap) {
    this.threatMap = threatMap;
  }

  @Override
  public void doAction() {

  }

  @Override
  public Constants.MOVE getMove(Game game) {

    Constants.MOVE bestMove = game.getPacmanLastMoveMade();
    int pacNode = game.getPacmanCurrentNodeIndex();

    int pillIndex = -1;

    int[] pills = game.getActivePowerPillsIndices();
    int d = 9999;
    for (int i = 0; i < pills.length; i++) {
      int pillD = game.getShortestPathDistance(pacNode, pills[i]);
      if (pillD < d) {
        d = pillD;
        pillIndex = pills[i];
      }
    }

    float currentThreat = threatMap[pacNode];
    float maxThreat = 0;
    Constants.MOVE[] moves = game.getPossibleMoves(pacNode);

    float leastThreatSlope = 5;

    for (Constants.MOVE move : moves) {

      int node = game.getNeighbour(pacNode, move);
      float threat = threatMap[node];
      maxThreat = Math.max(maxThreat, threat);
      float threatSlope = threat - currentThreat;

      if ( threatSlope < leastThreatSlope) {
        leastThreatSlope = threatSlope;
        bestMove = move;
      }
    }

    return bestMove;
  }

  @Override
  public IAction makeDecision(Game game) {
    return this;
  }
}
