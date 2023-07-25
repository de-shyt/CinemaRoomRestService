package cinema.buisnesslayer.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({
        "token",
        "ticket"
})
public class PurchasedTicketJson {
    @JsonProperty("token")
    private UUID token;

    @JsonProperty("ticket")
    private SeatJson seat;

    public PurchasedTicketJson(int row, int col) {
        this.token = UUID.randomUUID();
        this.seat = new SeatJson(row, col, true);
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public SeatJson getSeat() {
        return seat;
    }

    public void setTicket(SeatJson ticket) {
        this.seat = ticket;
    }
}
