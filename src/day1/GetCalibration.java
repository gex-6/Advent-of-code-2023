package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GetCalibration {

    private final String fileName;
    private final ArrayList<String> input;

    public GetCalibration(String filename) {
        this.fileName = filename;
        this.input = new ArrayList<>();
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public int countCalibrationValue() {
        int sum = 0;
        for (String line : input) {
            sum += Integer.parseInt(getValueOfLine(line));
        }
        return  sum;
    }

    private String getValueOfLine(String line) {
        StringBuilder valueBuilder = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                valueBuilder.append(line.charAt(i));
                break;
            }
        }

        for (int i = line.length() - 1; i >= 0; i--) {
            if (Character.isDigit(line.charAt(i))) {
                valueBuilder.append(line.charAt(i));
                break;
            }
        }

        return valueBuilder.toString();
    }

    public void readInput() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                input.add(line);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}