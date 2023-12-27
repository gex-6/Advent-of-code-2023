package day8;

public class Run {

    public static void main(String[] args) {

        String fileName = "src/day8/puzzleInput.txt";

        NavigateNetworkExt net = new NavigateNetworkExt(fileName);
        System.out.println();
        System.out.println(net.calculateSteps());
    }
}
