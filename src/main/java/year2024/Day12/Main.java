package year2024.Day12;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static year2024.Day12.Part1.createGarden;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    var input = Input.read("./src/main/java/year2024/Day12/input");
    long score = new Part1().solve_pt1(input);
    System.out.println("pt1: " + score);

    long score2 = new Main().solve_pt2(input);
    System.out.println("pt2: " + score2);
  }

  public long solve_pt2(char[][] field) {
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

  private long calculateVerticalFences(Set<Plant> region) {
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

  private List<List<Plant>> getVerticalAdjacentPlants(List<Plant> plants) {
    List<List<Plant>> resultingList = new ArrayList<>();
    Queue<Plant> queue = new LinkedList<>(plants);
    while(!queue.isEmpty()) {
      List<Plant> verticalAdjacentPlants = new ArrayList<>();
      verticalAdjacentPlants.add(queue.poll());
      for(Plant p : queue) {
        if(p.getCoordinate().r() == verticalAdjacentPlants.get(0).getCoordinate().r()-1) {
          verticalAdjacentPlants.add(0, p);
          queue.remove(p);
        }
        if (p.getCoordinate().r() == verticalAdjacentPlants.get(verticalAdjacentPlants.size()-1).getCoordinate().r()+1) {
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

  private List<Plant> getPlantsForCol(long col, Set<Plant> region) {
    List<Plant> plants = new ArrayList<>();
    for (Plant plant : region) {
      if (plant.getCoordinate().c() == col) {
        plants.add(plant);
      }
    }
    return plants;
  }


  private long getMostRightPlantCoordinate(Set<Plant> region) {
    long mostRight = Long.MIN_VALUE;
    for (Plant plant : region) {
      if (plant.getCoordinate().c() > mostRight) {
        mostRight = plant.getCoordinate().c();
      }
    }
    return mostRight;
  }
  private long getMostLeftPlantCoordinate(Set<Plant> region) {
    long mostLeft = Long.MAX_VALUE;
    for (Plant plant : region) {
      if (plant.getCoordinate().c() < mostLeft) {
        mostLeft = plant.getCoordinate().c();
      }
    }
    return mostLeft;
  }

  private long calculateFences(Set<Plant> region) {
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

  private long calculateHorizontalFences(Set<Plant> region) {
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

  private long getLowestPlantHeight(Set<Plant> region) {
    long lowestHeight = Long.MIN_VALUE;
    for (Plant plant : region) {
      if (plant.getCoordinate().r() > lowestHeight) {
        lowestHeight = plant.getCoordinate().r();
      }
    }
    return lowestHeight;
  }

  private long getHighestPlantHeight(Set<Plant> region) {
    long highestHeight = Long.MAX_VALUE;
    for (Plant plant : region) {
      if (plant.getCoordinate().r() < highestHeight) {
        highestHeight = plant.getCoordinate().r();
      }
    }
    return highestHeight;
  }

  private List<List<Plant>> getHorizontalAdjacentPlants(List<Plant> highestPlants) {
    List<List<Plant>> resultingList = new ArrayList<>();
    Queue<Plant> queue = new LinkedList<>(highestPlants);
    while(!queue.isEmpty()) {
      List<Plant> horizontalAdjacentPlants = new ArrayList<>();
      horizontalAdjacentPlants.add(queue.poll());
      for(Plant p : queue) {
        if(p.getCoordinate().c() == horizontalAdjacentPlants.get(0).getCoordinate().c()-1) {
          horizontalAdjacentPlants.add(0, p);
          queue.remove(p);
        }
        if (p.getCoordinate().c() == horizontalAdjacentPlants.get(horizontalAdjacentPlants.size()-1).getCoordinate().c()+1) {
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

  private List<Plant> getPlantsForHeight(long height, Set<Plant> region) {
    List<Plant> plants = new ArrayList<>();
    for (Plant plant : region) {
      if (plant.getCoordinate().r() == height) {
        plants.add(plant);
      }
    }
    return plants;
  }
}
