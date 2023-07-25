package cinema.buisnesslayer.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "current_income",
        "number_of_available_seats",
        "number_of_purchased_tickets"
})
public class CinemaRoomStatisticsJson {
    @JsonProperty("current_income")
    private int income;

    @JsonProperty("number_of_available_seats")
    private int availableSeatsAmount;

    @JsonProperty("number_of_purchased_tickets")
    private int purchasedTicketsAmount;

    public  CinemaRoomStatisticsJson() {}

    public CinemaRoomStatisticsJson(int income, int availableSeatsAmount, int purchasedTicketsAmount) {
        this.income = income;
        this.availableSeatsAmount = availableSeatsAmount;
        this.purchasedTicketsAmount = purchasedTicketsAmount;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailableSeatsAmount() {
        return availableSeatsAmount;
    }

    public void setAvailableSeatsAmount(int availableSeatsAmount) {
        this.availableSeatsAmount = availableSeatsAmount;
    }

    public int getPurchasedTicketsAmount() {
        return purchasedTicketsAmount;
    }

    public void setPurchasedTicketsAmount(int purchasedTicketsAmount) {
        this.purchasedTicketsAmount = purchasedTicketsAmount;
    }
}
