package year2024.Day10;

import utils.Coordinate;
import java.util.LinkedList;
import java.util.List;

public record Path(LinkedList<Coordinate> path) {
  public Path(int row, int col) {
    this(new LinkedList<>(List.of(new Coordinate(row, col))));
  }

  public Path(Path other) {
    this(new LinkedList<>(other.path));
  }

  public void addCoordinate(Coordinate coordinate) {
    path.add(coordinate);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Path that)) return false;
    for (int i = 0; i < path.size(); i++) {
      if (!path.get(i).equals(that.path.get(i))) {
        return false;
      }
    }
    return true;
  }

  void removeCoordinate(Coordinate coordinate) {
    path.removeIf(c -> c.equals(coordinate));
  }

  Coordinate getLastCoordinate() {
    if (path.isEmpty()) {
      throw new IllegalStateException("Path is empty, cannot get last coordinate.");
    }
    return path.getLast();
  }
}
