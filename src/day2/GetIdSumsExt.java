package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetIdSumsExt {
    private final String fileName;
    private final HashMap<Integer, String> input;

    public GetIdSumsExt(String filename) {
        this.fileName = filename;
        this.input = new HashMap<>();

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

    public int calculateTheMinBallsSum() {

        int sum = 0;

        for (HashMap.Entry<Integer, String> entry : input.entrySet()) {
            String game = entry.getValue();

            String[] sets = game.split(";");

            int blueCubesMin = 0;
            int redCubesMin = 0;
            int greenCubesMin = 0;

            for (String set : sets) {

                String[] cubesSet = set.split(",");

                for (String cubes : cubesSet) {

                    String[] parts = cubes.trim().split(" ");
                    int ballNumber = Integer.parseInt(parts[0]);
                    String color = parts[1];

                    switch (color) {
                        case "blue" -> {
                            if (ballNumber > blueCubesMin) blueCubesMin = ballNumber;
                        }
                        case "red" -> {
                            if (ballNumber > redCubesMin) redCubesMin = ballNumber;
                        }
                        case "green" -> {
                            if (ballNumber > greenCubesMin) greenCubesMin = ballNumber;
                        }
                    }
                }
            }
            sum += blueCubesMin * redCubesMin * greenCubesMin;
        }

        return sum;
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
                break;
            }
        }

        if (idBuilder.length() > 0) {
            id = Integer.parseInt(idBuilder.toString());
        }

        return id;
    }
}
