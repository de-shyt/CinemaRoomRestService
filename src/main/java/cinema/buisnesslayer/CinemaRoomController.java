package cinema.buisnesslayer;

import cinema.buisnesslayer.CinemaRoom.CinemaRoom;
import cinema.buisnesslayer.CinemaRoom.CinemaRoomManager;
import cinema.buisnesslayer.exceptions.TheSeatIsTakenException;
import cinema.buisnesslayer.exceptions.WrongPasswordException;
import cinema.buisnesslayer.exceptions.WrongTokenException;
import cinema.buisnesslayer.json.*;
import cinema.databaselayer.PurchasedTicketsDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CinemaRoomController {
    private final CinemaRoomManager manager;
    private final PurchasedTicketsDatabase database;

    @Autowired
    public CinemaRoomController(CinemaRoom cinemaRoom, PurchasedTicketsDatabase database) {
        this.manager = new CinemaRoomManager(cinemaRoom);
        this.database = database;
    }

    @GetMapping("/seats")
    public CinemaRoomJson getCinemaRoomInfo() {
        return new CinemaRoomJson(manager.getCinemaRoom());
    }

    @PostMapping("/purchase")
    public PurchasedTicketJson purchaseSeat(@RequestBody SeatJson seat) {
        int row = seat.getRow();
        int col = seat.getCol();

        CinemaRoomConfig.checkIndexConstraints(row, col, manager.getRowsAmount(), manager.getColumnsAmount());

        if (manager.processTicketPurchase(row, col)) {
            PurchasedTicketJson ticket = new PurchasedTicketJson(row, col);
            database.add(ticket.getToken(), ticket.getSeat());
            return ticket;
        }
        throw new TheSeatIsTakenException();
    }

    @PostMapping("/return")
    public ReturnedTicketJson returnTicket(@RequestBody TokenJson tokenJson) {
        UUID token = tokenJson.getToken();
        try {
            SeatJson seat = database.remove(token);
            manager.processTicketReturn(seat.getRow(), seat.getCol());
            return new ReturnedTicketJson(seat);
        } catch (Exception e) {
            throw new WrongTokenException();
        }
    }

    @GetMapping("/stats")
    public CinemaRoomStatisticsJson getStatistics(@RequestParam(value = "password", required = false) String pwd) {
        if (!manager.getPassword().equals(pwd)) {
            throw new WrongPasswordException();
        }
        return new CinemaRoomStatisticsJson(
                manager.getCurrentIncome(),
                manager.numberOfAvailableSeats(),
                manager.numberOfPurchasedSeats()
        );
    }
}
