package year2024.Day12;

public class Plant {
  private final Coordinate coordinate;
  private char plant;
  private int fenceCount;

  public Plant(int r, int c, char plant, char[][] field) {
    this.coordinate = new Coordinate(r, c);
    this.plant = plant;
    this.countFences(field);
  }

  private void countFences(char[][] field) {
    int count = 0;
    int r = coordinate.r();
    int c = coordinate.c();
    for (int i = r - 1; i <= r + 1; i++) {
      for (int j = c - 1; j <= c + 1; j++) {
        if ((j != c && i != r) || (j == c && i == r)) {
          continue; // skip diagonals
        }
        if (i < 0 || i >= field.length || j < 0 || j >= field[0].length) {
          count++;
        } else if (field[i][j] != plant) {
          count++;
        }
      }
    }
    this.fenceCount = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Plant that)) return false;
    return coordinate.r() == that.coordinate.r() && coordinate.c() == that.coordinate.c();
  }

  char getPlant() {
    return plant;
  }

  void setPlant(char value) {
    this.plant = value;
  }

  int getFenceCount() {
    return fenceCount;
  }

  void setFenceCount(int fenceCount) {
    this.fenceCount = fenceCount;
  }

  @Override
  public int hashCode() {
    return 31 * coordinate.r() + coordinate.c(); // Simple hash code based on row and column
  }

  Coordinate getCoordinate() {
    return coordinate;
  }
}
