package year2023.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class AlmanacFoodProductionSolver {

    public static void main(String[] args) throws FileNotFoundException {
        // long closestLocation = findClosestLocation("./src/main/java/year2023/Day5/input");
        // System.out.println(closestLocation);
        long closestLocationInitial = findClosestLocationEnlarged("./src/main/java/year2023/Day5/input");
        System.out.println(closestLocationInitial);
    }

    static long findClosestLocation(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        long[] seeds = Arrays.stream(sc.nextLine().split(": ")[1].split(" ")).mapToLong(Long::parseLong).toArray();
        sc.nextLine();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.isEmpty()) {
                continue;
            }
            if(line.contains("map")) {
                translateMap(sc, seeds);
            }

        }
        return Arrays.stream(seeds).min().getAsLong();
    }


    static long findClosestLocationEnlarged(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        long bestLocation = Long.MAX_VALUE;
        long[] seeds = Arrays.stream(sc.nextLine().split(": ")[1].split(" ")).mapToLong(Long::parseLong).toArray();
        for(int i = 0; i < seeds.length; i+=2) {
            for(long j = seeds[i]; i<seeds[i]+seeds[i+1]; j++) {
                sc = new Scanner(new File(path));
                long seed = 0;
                while(sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if(line.isEmpty()) {
                        continue;
                    }
                    if(line.contains("map")) {
                        seed = j;

                        long destinationStart = sc.nextLong();
                        long sourceStart = sc.nextLong();
                        long range = sc.nextLong();

                        boolean inRange = false;
                        if(seed >= sourceStart && seed < sourceStart+range) {
                            inRange = true;
                        }

                        if(!inRange) {
                            continue;
                        }
                        for(long q=0; q<range; q++) {
                            if(seed == sourceStart+q) {
                                seed = destinationStart+q;
                            }

                        }


                    }

                }
                if (seed < bestLocation) {
                    bestLocation = seed;
                }
            }
        }
        return Arrays.stream(seeds).min().getAsLong();
    }

    private static void getEndResult(Scanner sc, long seed) {

    }

    private static long[] enlargeSeeds(long[] seeds) {
        int size = 0;
        for(int i = 1; i < seeds.length; i+=2) {
            size += seeds[i];
        }
        long[] results = new long[size];
        int currentIndex = 0;
        for(int i = 0; i < seeds.length; i+=2) {
            for(long j = seeds[i]; j < seeds[i]+seeds[i+1]; j++) {
                results[currentIndex++] = j;
            }
        }
        return results;
    }

    private static void translateMap(Scanner sc, long[] seeds) {
        Set<Integer> visited = new HashSet<>();
        while(sc.hasNextLong()) {
            long destinationStart = sc.nextLong();
            long sourceStart = sc.nextLong();
            long range = sc.nextLong();

            boolean isInRange = false;
            for(long s : seeds) {
               if(s >= sourceStart && s < sourceStart+range) {
                   isInRange = true;
                   break;
               }
            }
            if(!isInRange) {
                continue;
            }
            SEED: for(int j = 0; j < seeds.length; j++) {
                for(long i=0; i<range; i++) {
                    if(seeds[j] == sourceStart+i && !visited.contains(j)) {
                        visited.add(j);
                        seeds[j] = destinationStart+i;
                        continue SEED;
                    }
                }
            }
        }
    }

}
