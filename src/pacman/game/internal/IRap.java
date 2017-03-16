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
}
