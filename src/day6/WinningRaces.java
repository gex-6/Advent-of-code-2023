package day6;

import day5.AlmanacReaderExt;

public class WinningRaces {

    public static void main(String[] args) {

        int race1 = countWinnings(56, 546);
        int race2 = countWinnings(97, 1927);
        int race3 = countWinnings(78, 1131);
        int race4 = countWinnings(75, 1139);
        System.out.println(race1 * race2 * race3 * race4);

    }

    private static int countWinnings(int duration, int record) {

        int winnings = 0;

        for (int i = 0; i < duration; i++) {
            int speed = i;
            int time = duration - i;
            int distance = speed * time;

            if (distance > record) {
                winnings++;
            }
        }

        return winnings;
    }
}
