package cinema.buisnesslayer.CinemaRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The class represents a cinema room with a grid of seats. Each seat can be either available or occupied.
 * The class allows you to check the availability of seats, purchase or return seats.
 * The state of the `CinemaRoom` object can be modified only by the `CinemaRoomManager` class.
 *
 * @see CinemaRoomManager
 */
public class CinemaRoom {
    private final SeatType[][] seats;
    private final int rowsAmount;
    private final int columnsAmount;

    public CinemaRoom(int rowsAmount, int columnsAmount) {
        this.rowsAmount = rowsAmount;
        this.columnsAmount = columnsAmount;

        seats = new SeatType[rowsAmount][columnsAmount];
        for (int row = 0; row < rowsAmount; ++row) {
            Arrays.fill(seats[row], 0, columnsAmount, SeatType.AVAILABLE);
        }
    }

    public int getRowsAmount() {
        return rowsAmount;
    }

    public int getColumnsAmount() {
        return columnsAmount;
    }

    public List<int[]> getFreeSeats() {
        List<int[]> result = new ArrayList<>();
        for (int row = 0; row < rowsAmount; ++row) {
            for (int col = 0; col < columnsAmount; ++col) {
                if (seats[row][col] == SeatType.AVAILABLE) {
                    result.add(new int[]{row + 1, col + 1});
                }
            }
        }
        return Collections.unmodifiableList(result);
    }

    /**
     * Makes the seat unavailable.
     * @param row - index of the row. 1 <= `row` <= `total_rows`.
     * @param col - index of the column. 1 <= `col` <= `total_columns`.
     */
    protected void purchaseSeat(int row, int col) {
        seats[row - 1][col - 1] = SeatType.OCCUPIED;
    }

    /**
     * Checks if the seat is available.
     * @param row - index of the row. 1 <= `row` <= `total_rows`.
     * @param col - index of the column. 1 <= `col` <= `total_columns`.
     * @return `true` if the seat is available, `false` otherwise.
     */
    protected boolean isSeatAvailable(int row, int col) {
        return seats[row - 1][col - 1] == SeatType.AVAILABLE;
    }

    /**
     * Makes the seat available.
     * @param row - index of the row. 1 <= `row` <= `total_rows`.
     * @param col - index of the column. 1 <= `col` <= `total_columns`.
     */
    protected void makeSeatAvailable(int row, int col) {
        seats[row - 1][col - 1] = SeatType.AVAILABLE;
    }

    private enum SeatType {
        AVAILABLE, OCCUPIED
    }
}
