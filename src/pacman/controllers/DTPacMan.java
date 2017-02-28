package pacman.controllers;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.internal.DecisionTree;


public class DTPacMan extends Controller {
    DecisionTree tree = new DecisionTree("decision tree");

    public Constants.MOVE getMove(Game game, long timeDue) {
        return Constants.MOVE.DOWN;
    }

}
