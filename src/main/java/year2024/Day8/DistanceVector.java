package year2024.Day8;

import java.util.ArrayList;
import java.util.List;

public record DistanceVector(int x1, int y1, int dx, int dy) {
  public DistanceVector(int[] c1, int[] c2) {
    this(c1[0], c1[1], c2[0] - c1[0], c2[1] - c1[1]);
  }

  List<int[]> antisymmetricPoints(char[][] grid) {
    List<int[]> points = new ArrayList<>();
    int[] newPoint = {x1 + (2 * dx), y1 + (2 * dy)};
    if (isValid(newPoint, grid)) {
      points.add(newPoint);
    }
    newPoint = new int[] {x1 - dx, y1 - dy};
    if (isValid(newPoint, grid)) {
      points.add(newPoint);
    }

    return points;
  }

  private boolean isValid(int[] coord, char[][] grid) {
    return coord[0] >= 0 && coord[0] < grid.length && coord[1] >= 0 && coord[1] < grid[0].length;
  }

  List<int[]> allAntisymmetricPoints(char[][] grid) {
    List<int[]> points = new ArrayList<>();
    points.add(new int[]{x1, y1});

    int[] newPoint = {x1 + dx, y1 +  dy};
    int i = 2;
    while (isValid(newPoint, grid)) {
      points.add(newPoint);
      newPoint = new int[] {x1 + (i * dx), y1 + (i * dy)};
      i++;
    }

    newPoint = new int[] {x1 - dx, y1 - dy};
    i = 2;
    while (isValid(newPoint, grid)) {
      points.add(newPoint);
      newPoint = new int[] {x1 - (i * dx), y1 - (i * dy)};
      i++;
    }

    return points;
  }
}
