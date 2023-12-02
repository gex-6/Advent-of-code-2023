package day2;

import java.util.HashMap;

public class Run {
    public static void main(String[] args) {

        String fileName = "src/day2/puzzleInput.txt";

        GetIdSums cubesGame = new GetIdSums(fileName);
        cubesGame.readInput();
        System.out.println(cubesGame.calculateBallsInGame());

        for (HashMap.Entry<Integer, String> entry : cubesGame.getInput().entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();
            System.out.println("ID: " + key + " --- Games: " + value);
        }

    }
}