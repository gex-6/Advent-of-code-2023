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
    private Long[][] seedsToSoilMap;
    private Long[][] soilToFertilizerMap;
    private Long[][] fertilizerToWaterMap;
    private Long[][] waterToLightMap;
    private Long[][] lightToTemperatureMap;
    private Long[][] temperatureToHumidityMap;
    private Long[][] humidityToLocationMap;

    public void calculateTheLocation() {
        long locationMin = -1;

        for (int i = 0; i < seeds.size(); i += 2) {
            long seed = seeds.get(i);
            long numberOfSeedsInPair = seeds.get(i + 1);

            for (int j = 0; j < numberOfSeedsInPair; j++) {
                long soil = sourceToDestination(seedsToSoilMap,seed + j);
                long fertilizer = sourceToDestination(soilToFertilizerMap, soil);
                long water = sourceToDestination(fertilizerToWaterMap, fertilizer);
                long light = sourceToDestination(waterToLightMap, water);
                long temperature = sourceToDestination(lightToTemperatureMap, light);
                long humidity = sourceToDestination(temperatureToHumidityMap, temperature);
                long location = sourceToDestination(humidityToLocationMap, humidity);
                locationMin = (locationMin == -1) ? location : Math.min(location, locationMin);
            }
        }

        System.out.println("The lowest location number is " + locationMin);
    }

    private long sourceToDestination(Long[][] map, long seed) {
        for (int i = 0; i < map.length; i++) {
            if (((seed - map[i][0]) >= 0) && ((seed - map[i][0]) < map[i][2])) {
                return ((seed - map[i][0]) + map[i][1]);
            }
        }
        return seed;
    }

    public AlmanacReaderExt(String fileName) {
        this.input = new ArrayList<>();
        this.seeds = new ArrayList<>();

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
        seedsToSoilMap = parseMap("seed-to-soil map:", "soil-to-fertilizer map:");
        soilToFertilizerMap = parseMap("soil-to-fertilizer map:", "fertilizer-to-water map:");
        fertilizerToWaterMap = parseMap("fertilizer-to-water map:", "water-to-light map:");
        waterToLightMap = parseMap("water-to-light map:", "light-to-temperature map:");
        lightToTemperatureMap = parseMap("light-to-temperature map:", "temperature-to-humidity map:");
        temperatureToHumidityMap = parseMap("temperature-to-humidity map:", "humidity-to-location map:");
        humidityToLocationMap = parseMap("humidity-to-location map:", "end");
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