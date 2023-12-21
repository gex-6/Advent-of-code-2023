package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CamelGame {

    private final List<String[]> camelCards;
    private final HashMap<Character, Integer> cardsValues;

    public void playGame() {
        sortHands();
        countWinnings();
    }

    private void countWinnings() {
        int i = 1;
        int sum = 0;
        for (String[] bit : camelCards) {
            sum += Integer.parseInt(bit[1]) * i;
            i++;
        }
        System.out.println(sum);
    }

    private void sortHands() {

        String[] temp;
        boolean swapped;

        for (int i = 0; i < camelCards.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < camelCards.size() - i - 1; j++) {

                if (isHandBigger(camelCards.get(j), camelCards.get(j + 1))) {

                    temp = camelCards.get(j);
                    camelCards.set(j, camelCards.get(j + 1));
                    camelCards.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public boolean isHandBigger(String[] hand1, String[] hand2) {
        String cards1 = hand1[0];
        String cards2 = hand2[0];

        int value1 = findValueOfHand(cards1);
        int value2 = findValueOfHand(cards2);

        if (value1 > value2) {
            return true;
        } else if (value1 == value2) {
            return compareFirstLetters(cards1, cards2);
        }

        return false;
    }

    public boolean compareFirstLetters(String hand1, String hand2) {
        char[] hand1Chars = hand1.toCharArray();
        char[] hand2Chars = hand2.toCharArray();

        for (int i = 0; i < 5; i++) {
            int value1 = cardsValues.get(hand1Chars[i]);
            int value2 = cardsValues.get(hand2Chars[i]);

            if (value1 > value2) {
                return true;
            } else if (value1 < value2) {
                return false;
            }
        }

        return false;
    }

    public static int findValueOfHand(String hand) {
        int[] count = new int[256]; // Assuming ASCII characters
        for (char c : hand.toCharArray()) {
            count[c]++;
        }

        boolean hasPair = false; //2
        boolean hasTwoPairs = false; //3
        boolean hasThreeOfAKind = false; //4
        boolean hasFullHouse = false; //5
        boolean hasFourOfAKind = false; //6
        boolean hasFiveOfAKind = false; //7

        for (int i : count) {
            if (i == 2) {
                if (hasPair) {
                    hasTwoPairs = true;
                    hasPair = false;
                } else if (hasThreeOfAKind) {
                    hasFullHouse = true;
                }
                else {
                    hasPair = true;
                }
            } else if (i == 3) {
                if (hasPair) {
                    hasFullHouse = true;
                    hasPair = false;
                } else {
                    hasThreeOfAKind = true;
                }
            } else if (i == 4) {
                hasFourOfAKind = true;
            } else if (i == 5) {
                hasFiveOfAKind = true;
            }
        }

        if (hasFiveOfAKind) {
            return 7;
        } else if (hasFourOfAKind) {
            return 6;
        } else if (hasFullHouse) {
            return 5;
        } else if (hasThreeOfAKind) {
            return 4;
        } else if (hasTwoPairs) {
            return 3;
        } else if (hasPair) {
            return 2;
        }
        return 1;
    }

    public CamelGame(String fileName) {
        camelCards = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s");
                String cards = parts[0];
                String bid = parts[1];

                camelCards.add(new String[]{cards, bid});

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.cardsValues = new HashMap<>();
        cardsValues.put('2', 1);
        cardsValues.put('3', 2);
        cardsValues.put('4', 3);
        cardsValues.put('5', 4);
        cardsValues.put('6', 5);
        cardsValues.put('7', 6);
        cardsValues.put('8', 7);
        cardsValues.put('9', 8);
        cardsValues.put('T', 9);
        cardsValues.put('J', 10);
        cardsValues.put('Q', 11);
        cardsValues.put('K', 12);
        cardsValues.put('A', 13);
    }
}