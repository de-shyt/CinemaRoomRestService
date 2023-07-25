package cinema.buisnesslayer.CinemaRoom;

import cinema.buisnesslayer.CinemaRoomConfig;

/**
 * The CinemaRoomManager class is responsible for managing operations related to a specific cinema room.
 * It provides methods to process ticket purchases and returns, track the income from ticket sales,
 * and obtain information about the available and purchased seats in the cinema room.
 *
 * @see CinemaRoom
 */
public class CinemaRoomManager {
    private final CinemaRoom cinemaRoom;
    private int income;
    private static final String password = "super_secret";


    public CinemaRoomManager(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public int getRowsAmount() {
        return cinemaRoom.getRowsAmount();
    }

    public int getColumnsAmount() {
        return cinemaRoom.getColumnsAmount();
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public String getPassword() { return password; }

    public int getCurrentIncome() {
        return income;
    }

    /**
     * Processes the purchase of a ticket for the given seat. If the seat is available,
     * the ticket is purchased, and the income is updated accordingly.
     *
     * @param row The index of the row for the seat (1 <= row <= total_rows).
     * @param col The index of the column for the seat (1 <= col <= total_columns).
     * @return true if the ticket was purchased successfully (seat available), false otherwise.
     */
    public boolean processTicketPurchase(int row, int col) {
        CinemaRoomConfig.checkIndexConstraints(row, col, cinemaRoom.getRowsAmount(), cinemaRoom.getColumnsAmount());
        if (cinemaRoom.isSeatAvailable(row, col)) {
            cinemaRoom.purchaseSeat(row, col);
            income += CinemaRoomConfig.generateSeatPrice(row, col);
            return true;
        }
        return false;
    }

    /**
     * Processes the return of a ticket, making the seat available again, and updating the income accordingly.
     *
     * @param row The index of the row for the seat (1 <= row <= total_rows).
     * @param col The index of the column for the seat (1 <= col <= total_columns).
     */
    public void processTicketReturn(int row, int col) {
        CinemaRoomConfig.checkIndexConstraints(row, col, cinemaRoom.getRowsAmount(), cinemaRoom.getColumnsAmount());
        if (!cinemaRoom.isSeatAvailable(row, col)) {
            cinemaRoom.makeSeatAvailable(row, col);
            income -= CinemaRoomConfig.generateSeatPrice(row, col);
        }
    }

    public int numberOfAvailableSeats() {
        int result = 0;
        for (int row = 1; row <= cinemaRoom.getRowsAmount(); ++row) {
            for (int col = 1; col <= cinemaRoom.getColumnsAmount(); ++col) {
                if (cinemaRoom.isSeatAvailable(row, col)) {
                    result++;
                }
            }
        }
        return result;
    }

    public int numberOfPurchasedSeats() {
        return cinemaRoom.getRowsAmount() * cinemaRoom.getColumnsAmount() - numberOfAvailableSeats();
    }
}
