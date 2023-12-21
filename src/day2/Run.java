package day2;

import java.util.HashMap;

public class Run {
    public static void main(String[] args) {

        String fileName = "src/day2/puzzleInput.txt";

        GetIdSumsExt cubesGame = new GetIdSumsExt(fileName);
        System.out.println("\n" + cubesGame.calculateTheMinBallsSum());
    }
}