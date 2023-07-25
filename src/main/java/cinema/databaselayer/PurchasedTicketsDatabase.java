package cinema.databaselayer;

import cinema.buisnesslayer.json.SeatJson;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PurchasedTicketsDatabase {
    private final Map<UUID, SeatJson> data = new HashMap<>();

    public SeatJson add(UUID token, SeatJson ticket) {
        return data.put(token, ticket);
    }

    public SeatJson get(UUID token) {
        return data.get(token);
    }

    public SeatJson remove(UUID token) {
        return data.remove(token);
    }
}
