package day7;

import day5.AlmanacReaderExt;

public class Run {

    public static void main(String[] args) {

        String fileName = "src/day7/puzzleInput.txt";

        CamelGameExt game = new CamelGameExt(fileName);
        game.playGame();
    }
}