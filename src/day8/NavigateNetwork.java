package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NavigateNetwork {

    private ArrayList<Boolean> directions;
    private HashMap<String, String[]> map;

    public Integer calculateSteps() {

        int steps = 0;
        String currentNode = "AAA";
        int dir = -1;

        while (!(currentNode.equals("ZZZ"))) {

            dir++;
            steps++;
            if (dir >= directions.size())
                dir = 0;

            //if true, then R (right)
            if (directions.get(dir))
                currentNode = map.get(currentNode)[1];
            else //if false, then L (left)
                currentNode = map.get(currentNode)[0];
        }

        return steps;
    }

    public NavigateNetwork(String fileName) {
        directions = new ArrayList<>();
        map = new HashMap<>();

        try {
            String firstLine = readFirstLine(fileName);
            directions = parseFirstLine(firstLine);

            map = parseRemainingLines(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFirstLine(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        }
    }

    private static ArrayList<Boolean> parseFirstLine(String line) {
        ArrayList<Boolean> booleanList = new ArrayList<>();
        for (char c : line.toCharArray()) {
            booleanList.add(c == 'R');
        }
        return booleanList;
    }

    private static HashMap<String, String[]> parseRemainingLines(String filePath) throws IOException {
        HashMap<String, String[]> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] parts = currentLine.split(" = ");
                String key = parts[0].trim();
                String[] values = parts[1].replaceAll("[()]", "").split(", ");
                map.put(key, values);
            }
        }

        return map;
    }
}
