package year2024.Day10;

import static aoc.utils.GridUtils.*;

import aoc.utils.Coordinate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrailHead {
  private final int row;
  private final int col;
  private final Set<Path> paths = new HashSet<>();
  private long accessibleTops;

  public TrailHead(int row, int col) {
    this.row = row;
    this.col = col;
    this.accessibleTops = 0;
  }

  int getCol() {
    return col;
  }

  long getAccessibleTops() {
    return accessibleTops;
  }

  long getPathsCount() {
    return paths.size();
  }

  void addScore() {
    this.accessibleTops++;
  }

  public int getRow() {
    return row;
  }

  void calculateAllPaths(int[][] map) {
    Path p = new Path(this.row, this.col);
    followPath(map, p);
  }

  private List<Coordinate> getAdjacentCoordinates(Coordinate c, int[][] input) {
    List<Coordinate> adjacent = new ArrayList<>();

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (Math.abs(i) + Math.abs(j) == 1) { // Only consider direct neighbors
          int newRow = c.row + i;
          int newCol = c.col + j;
          if (isValidCoordinate(newRow, newCol, input)
              && isGradualUphillSlope(input[c.row][c.col], input[newRow][newCol])) {
            adjacent.add(new Coordinate(newRow, newCol));
          }
        }
      }
    }

    return adjacent;
  }

  private void followPath(int[][] map, Path p) {
    List<Coordinate> queue = getAdjacentCoordinates(p.getLastCoordinate(), map);
    for (Coordinate coordinate : queue) {
      if (map[coordinate.row][coordinate.col] == 9) {
        p.addCoordinate(coordinate);
        this.paths.add(new Path(p));
        p.removeCoordinate(coordinate);
      } else {
        p.addCoordinate(coordinate);
        followPath(map, p);
        p.removeCoordinate(coordinate);
      }
    }
  }

  public void calculateAccessibleTops(int[][] map) {
    Set<Coordinate> visited = new HashSet<>();
    followTrail(this.row, this.col, map, visited);
  }

  private void followTrail(int row, int col, int[][] map, Set<Coordinate> visited) {
    List<Coordinate> queue = getAdjacentCoordinates(row, col, map, visited);
    for (Coordinate coordinate : queue) {
      row = coordinate.row;
      col = coordinate.col;
      if (map[row][col] == 9) {
        this.addScore();
      }
      visited.add(coordinate);
      if (map[row][col] < 9) {
        followTrail(row, col, map, visited);
      }
    }
  }

  private List<Coordinate> getAdjacentCoordinates(
      int row, int col, int[][] input, Set<Coordinate> visited) {
    List<Coordinate> adjacent = new ArrayList<>();

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (Math.abs(i) + Math.abs(j) == 1) { // Only consider direct neighbors
          int newRow = row + i;
          int newCol = col + j;
          if (isValidCoordinate(newRow, newCol, input)
              && isGradualUphillSlope(input[row][col], input[newRow][newCol])
              && !visited.contains(new Coordinate(newRow, newCol))) {
            adjacent.add(new Coordinate(newRow, newCol));
          }
        }
      }
    }

    return adjacent;
  }

  private boolean isGradualUphillSlope(int beginHeight, int newHeight) {
    return ++beginHeight == newHeight;
  }

  private boolean isValidCoordinate(int newRow, int newCol, int[][] input) {
    return inBounds(newRow, newCol, input.length, input[0].length);
  }
}
