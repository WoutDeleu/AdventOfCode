package year2024.Day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Part1 {


  public long solve_pt1(char[][] field) {
    Map<Coordinate, Plant> garden = createGarden(field);

    List<Set<Plant>> regions = new ArrayList<>();
    Set<Plant> visited = new HashSet<>();

    for (int r = 0; r < field.length; r++) {
      for (int c = 0; c < field[0].length; c++) {
        Plant plant = garden.get(new Coordinate(r, c));
        if (visited.add(plant)) {
          regions.add(collectAllAdjacentPlants(field, garden, visited, plant));
        }
      }
    }
    return calculateScore(regions);
  }
  public static  Map<Coordinate, Plant> createGarden(char[][] field) {
    Map<Coordinate, Plant> garden = new HashMap<>();
    for (int r = 0; r < field.length; r++) {
      for (int c = 0; c < field[0].length; c++) {
        Plant plant = new Plant(r, c, field[r][c], field);
        garden.put(plant.getCoordinate(), plant);
      }
    }
    return garden;
  }

  private  Queue<Plant> getAllAdjacents(
    Plant plant, char[][] field, Map<Coordinate, Plant> garden, Set<Plant> visited) {
    Queue<Plant> queue = new LinkedList<>();
    char currentChar = plant.getPlant();
    int r = plant.getCoordinate().r();
    int c = plant.getCoordinate().c();
    for (int i = r - 1; i <= r + 1; i++) {
      for (int j = c - 1; j <= c + 1; j++) {
        if ((j != c && i != r) || (j == c && i == r)) {
          continue; // skip diagonals
        }
        if (i < 0 || i >= field.length || j < 0 || j >= field[0].length) {
          continue;
        }
        if (field[i][j] == currentChar && !visited.contains(garden.get(new Coordinate(i, j)))) {
          queue.add(garden.get(new Coordinate(i, j)));
        }
      }
    }
    return queue;
  }
  private long calculateScore(List<Set<Plant>> regions) {
    long totalScore = 0;
    for (Set<Plant> region : regions) {
      long totalFences = 0;
      for (Plant plant : region) {
        totalFences += plant.getFenceCount();
      }
      totalScore += totalFences * region.size();
    }
    return totalScore;
  }


  public Set<Plant> collectAllAdjacentPlants(
    char[][] field, Map<Coordinate, Plant> garden, Set<Plant> visited, Plant plant) {
    Set<Plant> region = new HashSet<>();
    region.add(plant);
    Queue<Plant> queue = getAllAdjacents(plant, field, garden, visited);
    while (!queue.isEmpty()) {
      Plant adjacent = queue.poll();
      if (visited.add(adjacent)) {
        region.add(adjacent);
        queue.addAll(getAllAdjacents(adjacent, field, garden, visited));
      }
    }
    return region;
  }
}
