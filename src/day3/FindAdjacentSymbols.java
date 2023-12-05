package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FindAdjacentSymbols {

    private final String fileName;
    private final ArrayList<char[]> input;
    private char[][] parsedInput;

    private final ArrayList<String> results;

    public FindAdjacentSymbols(String filename) {
        this.fileName = filename;
        this.input = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public int countTheResult() {
        collectAdjacentNumbers();

        return results.stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private void collectAdjacentNumbers() {

        parsedInput = new char[input.size()][];
        parsedInput = input.toArray(parsedInput);

        for (int row = 0; row < parsedInput.length; row++) {
            for (int col = 0; col < parsedInput[row].length; col++) {

                String number = "";

                if (Character.isDigit(parsedInput[row][col])) {
                    number += parsedInput[row][col];
                    int next = 1;
                    try {
                        while (Character.isDigit(parsedInput[row][col + next])) {
                            number += parsedInput[row][col + next];
                            next++;
                        }
                    } catch (ArrayIndexOutOfBoundsException ignore) { }

                    checkTheSymbolsAround(number, row, col);

                    col += next;
                }
            }
        }
    }

    private void checkTheSymbolsAround(String number, int row, int col) {

        int[][] neighbours = new int[0][];

        if (number.length() == 1) {
            neighbours = new int[][]{
                    {row - 1, col - 1}, {row - 1, col}, {row - 1, col + 1},
                    {row, col - 1},                     {row, col + 1},
                    {row + 1, col - 1}, {row + 1, col}, {row + 1, col + 1}
            };
        } else if (number.length() == 2) {
            neighbours = new int[][]{
                    {row - 1, col - 1}, {row - 1, col}, {row - 1, col + 1}, {row - 1, col + 2},
                    {row, col - 1},                                         {row, col + 2},
                    {row + 1, col - 1}, {row + 1, col}, {row + 1, col + 1}, {row + 1, col + 2}
            };
        } else if (number.length() == 3) {
            neighbours = new int[][]{
                    {row - 1, col - 1}, {row - 1, col}, {row - 1, col + 1}, {row - 1, col + 2}, {row - 1, col + 3},
                    {row, col - 1},                                                             {row, col + 3},
                    {row + 1, col - 1}, {row + 1, col}, {row + 1, col + 1}, {row + 1, col + 2}, {row + 1, col + 3}
            };
        }

        try {
            for (int[] neighbour : neighbours) {
                int x = neighbour[0];
                int y = neighbour[1];

                if (isValidIndex(x, y) && isSymbol(parsedInput[x][y])) {
                    results.add(number);
                    return;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) { }
    }

    private boolean isValidIndex(int x, int y) {
        return x >= 0 && x < parsedInput.length && y >= 0 && y < parsedInput[x].length;
    }

    private static boolean isSymbol(char ch) {
        return ch == '*' || ch == '$' || ch == '#' || ch == '@' || ch == '=' || ch == '%' ||
                ch == '+' || ch == '-' || ch == '&' || ch == '/';
    }

    public void readInput() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                input.add(line.toCharArray());
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}