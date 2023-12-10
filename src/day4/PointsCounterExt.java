package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PointsCounterExt {

    private final ArrayList<String> input;

    public Integer findWinningNumbers() {
        ArrayList<Integer> resultCopies = new ArrayList<>();
        for (int i = 0; i < input.size(); i++ ) {
            resultCopies.add(i, 1);
        }
        int cardNumberPlayedNow = 0;

        try {
            for (int i = 0; i < input.size(); i++) {

                int iterations = resultCopies.get(i);

                while (iterations >= 1) {

                    String str = input.get(i);
                    int gameResult = cardNumberPlayedNow;

                    String[] parts = str.split("\\|");
                    int array1Length = parts[0].trim().split("\\s+").length;
                    int array2Length = parts[1].trim().split("\\s+").length;

                    int[] array1 = new int[array1Length];
                    int[] array2 = new int[array2Length];

                    for (int j = 0; j < array1Length; j++) {
                        array1[j] = Integer.parseInt(parts[0].trim().split("\\s+")[j]);
                    }
                    for (int j = 0; j < array2Length; j++) {
                        array2[j] = Integer.parseInt(parts[1].trim().split("\\s+")[j]);
                    }

                    Arrays.sort(array1);
                    Arrays.sort(array2);
                    for (Integer number : array2) {
                        int indexMatch = Arrays.binarySearch(array1, number);
                        if (indexMatch >= 0) {
                            gameResult++;
                            resultCopies.set(gameResult, (resultCopies.get(gameResult) + 1));
                        }
                    }

                    iterations--;
                }
                cardNumberPlayedNow++;

            }
        } catch (IndexOutOfBoundsException ignore) { }

        int result = 0;
        for (Integer num : resultCopies) {
            result += num;
        }
        return result;
    }

    public PointsCounterExt(String fileName) {
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
