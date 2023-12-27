package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NavigateNetworkExt {

    private ArrayList<Boolean> directions;
    private HashMap<String, String[]> map;
    private ArrayList<String> currentNodes;

    /*

    R - true - 1
    L - false - 0

     */
    public Long calculateSteps() {

        ArrayList<Long> results = new ArrayList<>();

        for (String key : map.keySet()) {
            if (key.endsWith("A"))
                currentNodes.add(key);
        }

        long steps;
        int dir;

        for (String node : currentNodes) {

            String currentNode = node;
            steps = 0;
            dir = -1;

            while (!(currentNode.endsWith("Z"))) {

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
            results.add(steps);

        }

        return findLCM(results);
    }

    public static long findLCM(ArrayList<Long> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list is empty or null.");
        }

        long lcm = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            lcm = findLCM(lcm, numbers.get(i));
        }

        return lcm;
    }

    public static long findLCM(long a, long b) {
        return (a * b) / findGCD(a, b);
    }

    public static long findGCD(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public NavigateNetworkExt(String fileName) {
        directions = new ArrayList<>();
        map = new HashMap<>();
        currentNodes = new ArrayList<>();

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
