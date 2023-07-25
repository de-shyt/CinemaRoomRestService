package cinema.buisnesslayer;

import cinema.buisnesslayer.CinemaRoom.CinemaRoom;
import cinema.buisnesslayer.exceptions.WrongSeatIndexException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaRoomConfig {

    @Bean("cinemaRoom")
    public CinemaRoom defaultCinemaRoom() {
        return new CinemaRoom(9, 9);
    }

    public static void checkIndexConstraints(int row, int col, int rowsAmount, int columnsAmount) {
        if (!(1 <= row && row <= rowsAmount)
                || !(1 <= col && col <= columnsAmount)) {
            throw new WrongSeatIndexException();
        }
    }

    public static int generateSeatPrice(int row, int col) {
        if (row < 1 || col < 1) {
            throw new WrongSeatIndexException();
        }
        if (row <= 4) {
            return 10;
        }
        return 8;
    }
}
