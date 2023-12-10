package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PointsCounter {

    private final String fileName;
    private final ArrayList<String> input;

    public int findWinningNumbers() {
        int result = 0;

        for (String str : input) {
            int gameResult = 0;

            String[] parts = str.split("\\|");
            int[] array1 = new int[parts[0].trim().split("\\s+").length];
            int[] array2 = new int[parts[1].trim().split("\\s+").length];

            for (int i = 0; i < parts[0].trim().split("\\s+").length; i++) {
                array1[i] = Integer.parseInt(parts[0].trim().split("\\s+")[i]);
            }
            for (int i = 0; i < parts[1].trim().split("\\s+").length; i++) {
                array2[i] = Integer.parseInt(parts[1].trim().split("\\s+")[i]);
            }

            Arrays.sort(array1);
            Arrays.sort(array2);
            for (Integer number : array2) {
                int indexMatch = Arrays.binarySearch(array1, number);
                if (indexMatch >= 0 && gameResult == 0) {
                    gameResult++;
                } else if (indexMatch >= 0) {
                    gameResult *= 2;
                }
            }
            result += gameResult;
        }
        return result;
    }

    public PointsCounter(String fileName) {
        this.fileName = fileName;
        this.input = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            String regex = "^Card \\d+: ";

            while ((line = br.readLine()) != null) {
                input.add(line.replaceAll(regex, ""));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
