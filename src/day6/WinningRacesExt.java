package day6;

public class WinningRacesExt {

    public static void main(String[] args) {

        System.out.println(countWinnings(56977875, 546192711311139L));

    }

    private static long countWinnings(long duration, long record) {

        long winnings = 0;

        for (long i = 0; i < duration; i++) {
            long distance = i * (duration - i);
            if (distance > record)
                winnings++;
        }

        return winnings;
    }
}