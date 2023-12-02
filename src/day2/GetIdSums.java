package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GetIdSums {
    private final String fileName;
    private final HashMap<Integer, String> input;

    private static final int RED_CUBES = 12;
    private static final int GREEN_CUBES = 13;
    private static final int BLUE_CUBES = 14;

    public GetIdSums(String filename) {
        this.fileName = filename;
        this.input = new HashMap<>();
    }

    public HashMap<Integer, String> getInput() {
        return input;
    }

    public Integer calculateBallsInGame() {

        int idSum = 0;

        for (HashMap.Entry<Integer, String> entry : input.entrySet()) {
            String game = entry.getValue();

            String[] sets = game.split(";");
            boolean approved = true; // Assume the set is approved unless proven otherwise

            for (String set : sets) {
                int blueCubes = 0;
                int redCubes = 0;
                int greenCubes = 0;

                String[] cubesSet = set.split(",");


                for (String cubes : cubesSet) {
                    String[] parts = cubes.trim().split(" ");
                    int count = Integer.parseInt(parts[0]);
                    String color = parts[1];

                    switch (color) {
                        case "blue" -> blueCubes += count;
                        case "red" -> redCubes += count;
                        case "green" -> greenCubes += count;
                    }

                    // Check if the total counts for each color in the set are within the allowed limits
                    if (blueCubes > BLUE_CUBES || redCubes > RED_CUBES || greenCubes > GREEN_CUBES) {
                        approved = false; // Set to false if any cube exceeds the limit
                        break; // No need to check further in this set
                    }
                }
            }
            if (approved) {
                System.out.println(entry.getKey());
                idSum += entry.getKey();
            }
        }

        return idSum;

    }

    public void readInput() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                input.put(getTheLineID(line), getTheLineValue(line));
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTheLineValue(String line) {
        int colonIndex = line.indexOf(':');
        return line.substring(colonIndex + 1).trim();
    }

    private int getTheLineID(String line) {
        int id = 0;
        StringBuilder idBuilder = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (Character.isDigit(currentChar)) {
                idBuilder.append(currentChar);
            } else if (idBuilder.length() > 0) {
                // If a sequence of digits has started and a non-digit is encountered, stop parsing
                break;
            }
        }

        // Convert the accumulated digits to an integer
        if (idBuilder.length() > 0) {
            id = Integer.parseInt(idBuilder.toString());
        }

        return id;
    }
}
