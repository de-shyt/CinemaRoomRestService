package cinema.buisnesslayer.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TokenJson {
    @JsonProperty("token")
    private UUID token;

    public TokenJson() {}

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
