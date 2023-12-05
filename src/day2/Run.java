package day2;

import java.util.HashMap;

public class Run {
    public static void main(String[] args) {

        String fileName = "src/day2/puzzleInput.txt";

        GetIdSums cubesGame = new GetIdSums(fileName);
        cubesGame.readInput();
        System.out.println(cubesGame.calculateBallsInGame());
    }
}