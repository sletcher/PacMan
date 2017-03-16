package pacman.controllers;

import pacman.game.Constants;
import pacman.game.Game;
import pacman.game.Constants.MOVE;
import pacman.game.internal.DecisionTreeNode;
import pacman.game.internal.IRap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class RAPPacMan extends Controller {
  public static LinkedList<Object> RAPS = new LinkedList<>();

  RAPPacMan() {
    parseFile("rap.txt");
  }

  public void parseFile(String fileName) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/ai" + System.getProperty("file.separator") + fileName)));
      String input = br.readLine();

      //preamble
      String[] pr = input.split("\t");

      int nodes = Integer.parseInt(pr[0]);

      input = br.readLine();
      while (input != null) {
        String[] nd = input.split("\t");

        input = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public MOVE getMove(Game game, long timeDue) {
    return MOVE.LEFT;
  }
}
