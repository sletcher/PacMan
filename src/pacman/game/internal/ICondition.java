package pacman.game.internal;

import pacman.game.Game;


public interface ICondition {
  enum CONDITION {
    EDIBLEGHOST, INEDIBLEGHOST, TRUE
  }

  public boolean test(Game game);
}
