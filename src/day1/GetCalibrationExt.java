package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCalibrationExt {

    private final ArrayList<String> input;
    private final Map<String, String> digitMap;

    public GetCalibrationExt(String filename) {
        this.input = new ArrayList<>();
        this.digitMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null) {
                input.add(line);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        digitMap.put("0", "0");
        digitMap.put("1", "1");
        digitMap.put("2", "2");
        digitMap.put("3", "3");
        digitMap.put("4", "4");
        digitMap.put("5", "5");
        digitMap.put("6", "6");
        digitMap.put("7", "7");
        digitMap.put("8", "8");
        digitMap.put("9", "9");
        digitMap.put("one", "1");
        digitMap.put("two", "2");
        digitMap.put("three", "3");
        digitMap.put("four", "4");
        digitMap.put("five", "5");
        digitMap.put("six", "6");
        digitMap.put("seven", "7");
        digitMap.put("eight", "8");
        digitMap.put("nine", "9");
    }

    public int countCalibrationValue() {
        int sum = 0;

        for (String line : input) {
            int indexFirst = -1;
            int indexLast = -1;
            String firstDigit = "";
            String lastDigit = "";
            String resultSting = "";

            for (String digit : digitMap.keySet()) {
                if (line.contains(digit)) {
                    if (indexFirst == -1 || line.indexOf(digit) < indexFirst) {
                        indexFirst = line.indexOf(digit);
                        firstDigit = "" + digitMap.get(digit);
                    }
                    if (indexLast == -1 || line.lastIndexOf(digit) > indexLast) {
                        indexLast = line.lastIndexOf(digit);
                        lastDigit = "" + digitMap.get(digit);
                    }
                }
            }
            resultSting += firstDigit + lastDigit;
            //System.out.println(line + " = " + resultSting);

            if (resultSting.length() != 0) {
                sum += Integer.parseInt(resultSting);
            }
        }
        return  sum;
    }
}
