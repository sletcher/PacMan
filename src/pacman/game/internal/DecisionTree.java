package pacman.game.internal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DecisionTree {
    public Integer distance;
    public DecisionTreeNode[] nodes;

    public DecisionTree(String fileName) {
        parseFile(fileName);
    }

    public void parseFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/AI" + System.getProperty("file.separator") + fileName)));
            String input = br.readLine();

            //preamble
            String[] pr = input.split("\t");

            distance = Integer.parseInt(pr[0]);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
