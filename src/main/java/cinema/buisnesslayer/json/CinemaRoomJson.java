package cinema.buisnesslayer.json;

import cinema.buisnesslayer.CinemaRoom.CinemaRoom;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "total_rows",
        "total_columns",
        "available_seats"
})
public class CinemaRoomJson {
    @JsonProperty("total_rows")
    private int rowsAmount;

    @JsonProperty("total_columns")
    private int columnsAmount;

    @JsonProperty("available_seats")
    private List<SeatJson> freeSeats;

    public CinemaRoomJson(CinemaRoom cinemaRoom) {
        rowsAmount = cinemaRoom.getRowsAmount();
        columnsAmount = cinemaRoom.getColumnsAmount();

        freeSeats = new ArrayList<>();
        freeSeats.addAll(cinemaRoom.getFreeSeats().stream().map(this::convertFreeSeatToJson).toList());
    }

    private SeatJson convertFreeSeatToJson(int[] indexes) {
        return new SeatJson(indexes[0], indexes[1], true);
    }
}
