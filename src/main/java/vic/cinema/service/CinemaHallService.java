package vic.cinema.service;

import java.util.List;
import vic.cinema.model.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall get(Long id);
}
