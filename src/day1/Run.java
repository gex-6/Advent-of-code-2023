package day1;

public class Run {
    public static void main(String[] args) {

        String fileName = "src/day1/puzzleInput.txt";

        GetCalibration calibrate = new GetCalibration(fileName);
        calibrate.readInput();
        System.out.println(calibrate.countCalibrationValue());
    }
}
