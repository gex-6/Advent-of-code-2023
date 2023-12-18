package day5;

public class Run {

    public static void main(String[] args) {


        String fileName = "src/day5/puzzleInput.txt";

        AlmanacReaderExt reader = new AlmanacReaderExt(fileName);
        reader.calculateTheLocation();
    }
}
