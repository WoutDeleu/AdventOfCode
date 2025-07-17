package year2024.Day10;

public record Coordinate(int row, int col) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Coordinate)) return false;
    Coordinate that = (Coordinate) o;
    return row == that.row && col == that.col;
  }
}
