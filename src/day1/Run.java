package day1;

public class Run {
    public static void main(String[] args) {

        String fileName = "src/day1/puzzleInput.txt";

        GetCalibrationExt calibrate = new GetCalibrationExt(fileName);
        System.out.println(calibrate.countCalibrationValue());
    }
}
