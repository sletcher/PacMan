package pacman.game.internal;

import pacman.game.Constants;
import pacman.game.Game;

public interface IRap {
    enum rap {
        EVADEGHOSTS, TARGETPOWERPILL, TARGETPILL
    }

    enum type {
        RAP, PRIMITIVE
    }

    /**
     * If the goal is already completed, return true, this RAP will be removed from the queue
     * @param game game
     * @return returns whether or not the goal is completed
     */
    Boolean checkGoal(Game game);

    /**
     * Checks the validity of the RAP
     * @param game game
     * @return returns whether or not the goal is completed
     */
    Boolean checkValidity(Game game);

    /**
     * Executes the RAPs primitive RAPs (associated actions)
     * @param game game
     * @return designates whether or not the RAP is returning an action
     */
    boolean handleRap(Game game);
}
