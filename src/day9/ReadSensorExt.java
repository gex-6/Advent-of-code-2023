package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReadSensorExt {

    private ArrayList<String> historyCode;

    /**
     * no refactoring here :D
     */
    public long scan() {
        long result = 0;
        ArrayList<ArrayList<Integer>> history = new ArrayList<>();

        for (String line : historyCode) {

            //додаємо перший рядок в масив
            Scanner scanner = new Scanner(line);
            ArrayList<Integer> row = new ArrayList<>();
            while (scanner.hasNext()) {
                int number = scanner.nextInt();
                row.add(number);
            }
            Collections.reverse(row);
            history.add(row);
            scanner.close();

            //дійти до нулів
            int lineOfHistory = 0;
            while (allElementsAreNotZeros(history.get(lineOfHistory))) {
                ArrayList<Integer> tempLine = new ArrayList<>();
                for (int i = 1; i < history.get(lineOfHistory).size(); i++) {
                    tempLine.add(history.get(lineOfHistory).get(i) - history.get(lineOfHistory).get(i - 1));
                }
                history.add(tempLine);
                lineOfHistory++;
            }

            //познаходити крайні ліві значення
            history.get(history.size() - 1).add(0);

            for (int i = history.size() - 2; i >= 0; i--) {
                int lastNumInRow = history.get(i).get(history.get(i).size() - 1);
                int lastNumInNextRow = history.get(i + 1).get(history.get(i + 1).size() - 1);
                history.get(i).add(lastNumInRow + lastNumInNextRow);
            }
            result += history.get(0).get(history.get(0).size() - 1);

            history.clear();
        }

        System.out.println(result);
        return result;
    }

    private boolean allElementsAreNotZeros(ArrayList<Integer> tempLine) {
        for (Integer num : tempLine) {
            if (num != 0) {
                return true;
            }
        }
        return false;
    }

    public ReadSensorExt(String fileName) {
        historyCode = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {

                historyCode.add(line);

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
