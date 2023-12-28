package day9;

import day8.NavigateNetworkExt;

public class Run {

    public static void main(String[] args) {

        String fileName = "src/day9/puzzleInput.txt";

        ReadSensor sens = new ReadSensor(fileName);
        sens.scan();
    }
}
