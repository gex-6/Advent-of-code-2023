package day3;

public class Run {

    public static void main(String[] args) {

        String fileName = "src/day3/puzzleInput.txt";

        FindAdjacentSymbols findSymbols = new FindAdjacentSymbols(fileName);
        findSymbols.readInput();

        System.out.println(findSymbols.countTheResult());
    }
}
