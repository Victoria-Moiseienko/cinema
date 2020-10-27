package vic.cinema.mapper;

import org.springframework.stereotype.Component;
import vic.cinema.dto.CinemaHallRequestDto;
import vic.cinema.dto.CinemaHallResponseDto;
import vic.cinema.model.CinemaHall;

@Component
public class CinemaHallMapper {

    public CinemaHall toCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        return cinemaHall;
    }

    public static CinemaHallResponseDto toDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setCinemaHallId(cinemaHall.getId());
        dto.setCapacity(cinemaHall.getCapacity());
        dto.setDescription(cinemaHall.getDescription());
        return dto;
    }
}
