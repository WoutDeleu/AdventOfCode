package year2024.Day12;

public class Coordinate {
  private final int r;
  private final int c;
  private char plant;
  private int fenceCount;

  public Coordinate(int r, int c, char plant) {
    this.r = r;
    this.c = c;
    this.plant = plant;
  }

  int getR() {
    return r;
  }

  int getC() {
    return c;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Coordinate that)) return false;
    return r == that.r && c == that.c;
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
    return 31 * r + c; // Simple hash code based on row and column
  }
}
