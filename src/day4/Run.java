package day4;

import day3.FindAdjacentSymbols;

public class Run {

    public static void main(String[] args) {

        String fileName = "src/day4/puzzleInput.txt";

        PointsCounterExt counter = new PointsCounterExt(fileName);
        System.out.println("Winnings " + counter.findWinningNumbers());


    }
}
