package cinema.buisnesslayer.json;

import cinema.buisnesslayer.CinemaRoomConfig;
import cinema.buisnesslayer.exceptions.WrongSeatIndexException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "row",
        "column",
        "price"
})
public class SeatJson {
    @JsonProperty("row")
    private int row;

    @JsonProperty("column")
    private int col;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("price")
    private Integer price;

    public SeatJson() {}

    public SeatJson(int row, int col, boolean includePrice) {
        this.row = row;
        this.col = col;
        if (includePrice) {
            this.price = CinemaRoomConfig.generateSeatPrice(row, col);
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "SeatJson { row=" + row + ", col=" + col + " }";
    }
}
