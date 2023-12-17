package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlmanacReaderExt {

    private final List<String> input;
    private final List<Long> seeds;
    private final List<Long> realSeeds;
    private List<Long> currentIds;
    private Long[][] seedsToSoilMap;
    private Long[][] soilToFertilizerMap;
    private Long[][] fertilizerToWaterMap;
    private Long[][] waterToLightMap;
    private Long[][] lightToTemperatureMap;
    private Long[][] temperatureToHumidityMap;
    private Long[][] humidityToLocationMap;

    public void calculateTheLocation() {
        currentIds = new ArrayList<>(realSeeds);

        sourceToDestination(seedsToSoilMap);
        sourceToDestination(soilToFertilizerMap);
        sourceToDestination(fertilizerToWaterMap);
        sourceToDestination(waterToLightMap);
        sourceToDestination(lightToTemperatureMap);
        sourceToDestination(temperatureToHumidityMap);
        sourceToDestination(humidityToLocationMap);

        Long result = findMinLong(currentIds);
        System.out.println("The lowest location number is " + result);
    }

    private Long findMinLong(List<Long> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        Long minLong = list.get(0);

        for (Long currentLong : list) {
            if (currentLong < minLong) {
                minLong = currentLong;
            }
        }

        return minLong;
    }

    private void sourceToDestination(Long[][] map) {
        for (int k = 0; k < currentIds.size(); k++) {
            Long currentNumber = realSeeds.get(k);
            Long correspondingNum = null;

            for (int i = 0; i < map.length; i++) {
                int j = 0;
                long compareLow = currentNumber.compareTo(map[i][j + 1]);
                long compareHigh = currentNumber.compareTo(map[i][j + 1] + map[i][j + 2]);

                if (compareLow >= 0 && compareHigh <= 0) {
                    Long indexDif = currentNumber - map[i][j + 1];
                    correspondingNum = map[i][j] + indexDif;
                    currentIds.set(k, correspondingNum);
                    break;
                }
            }
            if (correspondingNum == null) {
                currentIds.set(k, currentNumber);
            }
        }
    }

    public AlmanacReaderExt(String fileName) {
        this.input = new ArrayList<>();
        this.seeds = new ArrayList<>();
        this.realSeeds = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseInput();
    }

    private void parseInput() {
        getSeeds();
        getRealNumberOfSeeds();
        seedsToSoilMap = parseMap("seed-to-soil map:", "soil-to-fertilizer map:");
        soilToFertilizerMap = parseMap("soil-to-fertilizer map:", "fertilizer-to-water map:");
        fertilizerToWaterMap = parseMap("fertilizer-to-water map:", "water-to-light map:");
        waterToLightMap = parseMap("water-to-light map:", "light-to-temperature map:");
        lightToTemperatureMap = parseMap("light-to-temperature map:", "temperature-to-humidity map:");
        temperatureToHumidityMap = parseMap("temperature-to-humidity map:", "humidity-to-location map:");
        humidityToLocationMap = parseMap("humidity-to-location map:", "end");
    }

    private void getRealNumberOfSeeds() {
        for (int i = 0; i < seeds.size(); i += 2) {
            Long currentNumber = seeds.get(i);
            realSeeds.add(currentNumber);

            Long amountOfOperations = seeds.get(i + 1);

            while (amountOfOperations > 0) {
                currentNumber = currentNumber + 1;
                realSeeds.add(currentNumber);

                amountOfOperations--;
            }
        }
    }

    private Long[][] parseMap(String s, String s1) {
        int startIndex = input.indexOf(s) + 1;
        int endIndex = input.indexOf(s1) - 1;

        List<Long[]> longList = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            String[] numbers = input.get(i).split("\\s+");
            Long[] row = new Long[numbers.length];

            for (int j = 0; j < numbers.length; j++) {
                row[j] = Long.parseLong(numbers[j]);
            }
            longList.add(row);
        }

        return longList.toArray(new Long[0][]);
    }

    private void getSeeds() {
        String[] parts = input.get(0).split("\\s+");

        List<String> seedsStr = Arrays.stream(parts)
                .filter(s -> s.matches("\\d+"))
                .toList();

        for (String numericString : seedsStr) {
            seeds.add(Long.parseLong(numericString));
        }
    }
}