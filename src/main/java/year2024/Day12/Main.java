package year2024.Day12;

import static utils.ParsingUtils.*;
import static year2024.Day12.Part1.createGarden;

import utils.Coordinate;
import utils.InputReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        var input = InputReader.readLines(2024, 12);

        var part1 = solvePart1(input);
        System.out.println("Part 1: " + part1);

        var part2 = solvePart2(input);
        System.out.println("Part 2: " + part2);
    }

    static Object solvePart1(List<String> lines) {
        char[][] field = parseInput(lines);
        return new Part1().solve_pt1(field);
    }

    static Object solvePart2(List<String> lines) {
        char[][] field = parseInput(lines);
        Map<Coordinate, Plant> garden = createGarden(field);

        List<Set<Plant>> regions = new ArrayList<>();
        Set<Plant> visited = new HashSet<>();

        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[0].length; c++) {
                Plant plant = garden.get(new Coordinate(r, c));
                if (visited.add(plant)) {
                    regions.add(new Part1().collectAllAdjacentPlants(field, garden, visited, plant));
                }
            }
        }

        List<Long> sides = new ArrayList<>();
        for (Set<Plant> region : regions) {
            long totalFences = 0;
            totalFences += calculateHorizontalFences(region);
            totalFences += calculateVerticalFences(region);
            sides.add(totalFences);
        }
        return 0L;
    }

    private static char[][] parseInput(List<String> lines) {
        return parseGrid(lines);
    }

    private static long calculateVerticalFences(Set<Plant> region) {
        long totalFences = 0;
        long mostLeftPlantCoordinate = getMostLeftPlantCoordinate(region);
        long mostRightPlantCoordinate = getMostRightPlantCoordinate(region);
        for (long col = mostLeftPlantCoordinate; col >= mostRightPlantCoordinate; col++) {
            List<Plant> plants = getPlantsForCol(col, region);
            List<List<Plant>> verticalAdjacentPlants = getVerticalAdjacentPlants(plants);
            totalFences += verticalAdjacentPlants.size();
        }
        return totalFences;
    }

    private static List<List<Plant>> getVerticalAdjacentPlants(List<Plant> plants) {
        List<List<Plant>> resultingList = new ArrayList<>();
        Queue<Plant> queue = new LinkedList<>(plants);
        while(!queue.isEmpty()) {
            List<Plant> verticalAdjacentPlants = new ArrayList<>();
            verticalAdjacentPlants.add(queue.poll());
            for(Plant p : queue) {
                if(p.getCoordinate().row == verticalAdjacentPlants.get(0).getCoordinate().row-1) {
                    verticalAdjacentPlants.add(0, p);
                    queue.remove(p);
                }
                if (p.getCoordinate().row == verticalAdjacentPlants.get(verticalAdjacentPlants.size()-1).getCoordinate().row+1) {
                    verticalAdjacentPlants.add(p);
                    queue.remove(p);
                }
            }
            if (!queue.isEmpty()) {
                resultingList.add(verticalAdjacentPlants);
                verticalAdjacentPlants.clear();
            }
        }
        return resultingList;
    }

    private static List<Plant> getPlantsForCol(long col, Set<Plant> region) {
        List<Plant> plants = new ArrayList<>();
        for (Plant plant : region) {
            if (plant.getCoordinate().col == col) {
                plants.add(plant);
            }
        }
        return plants;
    }

    private static long getMostRightPlantCoordinate(Set<Plant> region) {
        long mostRight = Long.MIN_VALUE;
        for (Plant plant : region) {
            if (plant.getCoordinate().col > mostRight) {
                mostRight = plant.getCoordinate().col;
            }
        }
        return mostRight;
    }

    private static long getMostLeftPlantCoordinate(Set<Plant> region) {
        long mostLeft = Long.MAX_VALUE;
        for (Plant plant : region) {
            if (plant.getCoordinate().col < mostLeft) {
                mostLeft = plant.getCoordinate().col;
            }
        }
        return mostLeft;
    }

    private static long calculateHorizontalFences(Set<Plant> region) {
        long totalFences = 0;
        long highestPlantHeight = getHighestPlantHeight(region);
        long lowestPlantHeight = getLowestPlantHeight(region);
        for (long height = highestPlantHeight; height >= lowestPlantHeight; height++) {
            List<Plant> highestPlants = getPlantsForHeight(height, region);
            List<List<Plant>> horizontalAdjacentPlants = getHorizontalAdjacentPlants(highestPlants);
            totalFences += horizontalAdjacentPlants.size();
        }
        return totalFences;
    }

    private static long getLowestPlantHeight(Set<Plant> region) {
        long lowestHeight = Long.MIN_VALUE;
        for (Plant plant : region) {
            if (plant.getCoordinate().row > lowestHeight) {
                lowestHeight = plant.getCoordinate().row;
            }
        }
        return lowestHeight;
    }

    private static long getHighestPlantHeight(Set<Plant> region) {
        long highestHeight = Long.MAX_VALUE;
        for (Plant plant : region) {
            if (plant.getCoordinate().row < highestHeight) {
                highestHeight = plant.getCoordinate().row;
            }
        }
        return highestHeight;
    }

    private static List<List<Plant>> getHorizontalAdjacentPlants(List<Plant> highestPlants) {
        List<List<Plant>> resultingList = new ArrayList<>();
        Queue<Plant> queue = new LinkedList<>(highestPlants);
        while(!queue.isEmpty()) {
            List<Plant> horizontalAdjacentPlants = new ArrayList<>();
            horizontalAdjacentPlants.add(queue.poll());
            for(Plant p : queue) {
                if(p.getCoordinate().col == horizontalAdjacentPlants.get(0).getCoordinate().col-1) {
                    horizontalAdjacentPlants.add(0, p);
                    queue.remove(p);
                }
                if (p.getCoordinate().col == horizontalAdjacentPlants.get(horizontalAdjacentPlants.size()-1).getCoordinate().col+1) {
                    horizontalAdjacentPlants.add(p);
                    queue.remove(p);
                }
            }
            if (!queue.isEmpty()) {
                resultingList.add(horizontalAdjacentPlants);
                horizontalAdjacentPlants.clear();
            }
        }
        return resultingList;
    }

    private static List<Plant> getPlantsForHeight(long height, Set<Plant> region) {
        List<Plant> plants = new ArrayList<>();
        for (Plant plant : region) {
            if (plant.getCoordinate().row == height) {
                plants.add(plant);
            }
        }
        return plants;
    }
}
