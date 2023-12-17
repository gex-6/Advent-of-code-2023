package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlmanacReader {

    private final ArrayList<String> input;
    private final ArrayList<BigInteger> seeds;
    private ArrayList<BigInteger> currentIds;
    private BigInteger[][] seedsToSoilMap;
    private BigInteger[][] soilToFertilizerMap;
    private BigInteger[][] fertilizerToWaterMap;
    private BigInteger[][] waterToLightMap;
    private BigInteger[][] lightToTemperatureMap;
    private BigInteger[][] temperatureToHumidityMap;
    private BigInteger[][] humidityToLocationMap;

    public void calculateTheLocation() {

        currentIds = seeds;

        sourceToDestination(seedsToSoilMap);
        sourceToDestination(soilToFertilizerMap);
        sourceToDestination(fertilizerToWaterMap);
        sourceToDestination(waterToLightMap);
        sourceToDestination(lightToTemperatureMap);
        sourceToDestination(temperatureToHumidityMap);
        sourceToDestination(humidityToLocationMap);

        BigInteger result = findMinBigInteger(currentIds);
        System.out.println("The lowest location number is " + result);
    }

    private BigInteger findMinBigInteger(ArrayList<BigInteger> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        BigInteger minBigInteger = list.get(0);

        for (BigInteger currentBigInteger : list) {
            if (currentBigInteger.compareTo(minBigInteger) < 0) {
                minBigInteger = currentBigInteger;
            }
        }

        return minBigInteger;
    }

    private void sourceToDestination(BigInteger[][] map) {
        for (int k = 0; k < currentIds.size(); k++) {

            BigInteger currentNumber = seeds.get(k);
            BigInteger indexDif, correspondingNum = null;

            for (int i = 0; i < map.length; i++) {

                int j = 0;
                int compareLow = currentNumber.compareTo(map[i][j + 1]);
                int compareHigh = currentNumber.compareTo(map[i][j + 1].add(map[i][j + 2]));

                if (compareLow >= 0 && compareHigh <= 0) {

                    indexDif = currentNumber.subtract(map[i][j + 1]);
                    correspondingNum = map[i][j].add(indexDif);
                    currentIds.set(k, correspondingNum);
                }
            }
            if (correspondingNum == null) {
                currentIds.set(k, currentNumber);
            }
        }
    }

    public AlmanacReader(String fileName) {
        this.input = new ArrayList<>();
        this.seeds = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                input.add(line);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        parseInput();
    }

    private void parseInput() {

        getSeeds();
        seedsToSoilMap = parseMap("seed-to-soil map:", "soil-to-fertilizer map:");
        soilToFertilizerMap = parseMap("soil-to-fertilizer map:", "fertilizer-to-water map:");
        fertilizerToWaterMap = parseMap("fertilizer-to-water map:", "water-to-light map:");
        waterToLightMap = parseMap("water-to-light map:", "light-to-temperature map:");
        lightToTemperatureMap = parseMap("light-to-temperature map:", "temperature-to-humidity map:");
        temperatureToHumidityMap = parseMap("temperature-to-humidity map:", "humidity-to-location map:");
        humidityToLocationMap = parseMap("humidity-to-location map:", "end");
    }

    private BigInteger[][] parseMap(String s, String s1) {
        int startIndex = input.indexOf(s) + 1;
        int endIndex = input.indexOf(s1) - 1;

        List<BigInteger[]> bigIntList = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            String[] numbers = input.get(i).split("\\s+");
            BigInteger[] row = new BigInteger[numbers.length];

            for (int j = 0; j < numbers.length; j++) {
                row[j] = new BigInteger(numbers[j]);
            }
            bigIntList.add(row);
        }

        return bigIntList.toArray(new BigInteger[0][]);
    }

    private void getSeeds() {
        String[] parts = input.get(0).split("\\s+");

        List<String> seedsStr = Arrays.stream(parts)
                .filter(s -> s.matches("\\d+"))
                .toList();

        for (String numericString : seedsStr) {
            seeds.add(new BigInteger(numericString));
        }
    }
}