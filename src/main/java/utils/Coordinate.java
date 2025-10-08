package aoc.utils;

import java.util.Objects;

/**
 * Immutable 2D coordinate for grid-based puzzles
 * Supports common operations like movement, distance calculations, etc.
 */
public class Coordinate {
    public final int row;
    public final int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Move in a direction by a distance
     */
    public Coordinate move(Direction direction, int distance) {
        return new Coordinate(
            row + direction.dr * distance,
            col + direction.dc * distance
        );
    }

    /**
     * Move one step in a direction
     */
    public Coordinate move(Direction direction) {
        return move(direction, 1);
    }

    /**
     * Move by delta row and column
     */
    public Coordinate move(int dr, int dc) {
        return new Coordinate(row + dr, col + dc);
    }

    /**
     * Get all 4 adjacent neighbors (up, down, left, right)
     */
    public Coordinate[] neighbors4() {
        return new Coordinate[]{
            move(Direction.UP),
            move(Direction.DOWN),
            move(Direction.LEFT),
            move(Direction.RIGHT)
        };
    }

    /**
     * Get all 8 neighbors (including diagonals)
     */
    public Coordinate[] neighbors8() {
        return new Coordinate[]{
            move(-1, -1), move(-1, 0), move(-1, 1),
            move(0, -1),               move(0, 1),
            move(1, -1),  move(1, 0),  move(1, 1)
        };
    }

    /**
     * Manhattan distance to another coordinate
     */
    public int manhattanDistance(Coordinate other) {
        return Math.abs(row - other.row) + Math.abs(col - other.col);
    }

    /**
     * Euclidean distance to another coordinate
     */
    public double euclideanDistance(Coordinate other) {
        int dr = row - other.row;
        int dc = col - other.col;
        return Math.sqrt(dr * dr + dc * dc);
    }

    /**
     * Check if this coordinate is within bounds
     */
    public boolean inBounds(int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    /**
     * Direction enum for grid movement
     */
    public enum Direction {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1),
        UP_LEFT(-1, -1),
        UP_RIGHT(-1, 1),
        DOWN_LEFT(1, -1),
        DOWN_RIGHT(1, 1);

        public final int dr;
        public final int dc;

        Direction(int dr, int dc) {
            this.dr = dr;
            this.dc = dc;
        }

        /**
         * Turn 90 degrees clockwise
         */
        public Direction turnRight() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
                default -> this;
            };
        }

        /**
         * Turn 90 degrees counter-clockwise
         */
        public Direction turnLeft() {
            return switch (this) {
                case UP -> LEFT;
                case LEFT -> DOWN;
                case DOWN -> RIGHT;
                case RIGHT -> UP;
                default -> this;
            };
        }

        /**
         * Get opposite direction
         */
        public Direction opposite() {
            return switch (this) {
                case UP -> DOWN;
                case DOWN -> UP;
                case LEFT -> RIGHT;
                case RIGHT -> LEFT;
                case UP_LEFT -> DOWN_RIGHT;
                case DOWN_RIGHT -> UP_LEFT;
                case UP_RIGHT -> DOWN_LEFT;
                case DOWN_LEFT -> UP_RIGHT;
            };
        }
    }
}
