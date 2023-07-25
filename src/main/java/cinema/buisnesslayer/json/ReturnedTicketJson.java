package cinema.buisnesslayer.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicketJson {
    @JsonProperty("returned_ticket")
    private SeatJson seat;

    public ReturnedTicketJson(int row, int col) {
        this.seat = new SeatJson(row, col, true);
    }

    public ReturnedTicketJson(SeatJson seat) {
        this.seat = seat;
    }
}
