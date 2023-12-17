package day5;

public class Run {

    public static void main(String[] args) {


        String fileName = "src/day5/puzzleInput.txt";

        AlmanacReader reader = new AlmanacReader(fileName);
        reader.calculateTheLocation();
    }
}
