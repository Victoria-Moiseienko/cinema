package vic.cinema.mapper;

import org.springframework.stereotype.Component;
import vic.cinema.dto.CinemaHallRequestDto;
import vic.cinema.dto.CinemaHallResponseDto;
import vic.cinema.model.CinemaHall;

@Component
public class CinemaHallMapper {
    public CinemaHall toCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {

    }

    public CinemaHallResponseDto toDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();

    }
}
