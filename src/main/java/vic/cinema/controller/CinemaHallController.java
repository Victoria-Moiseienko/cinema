package vic.cinema.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vic.cinema.dto.CinemaHallRequestDto;
import vic.cinema.model.CinemaHall;
import vic.cinema.service.CinemaHallService;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private static CinemaHallService cinemaHallService;

    //Add cinema hall - POST: /cinema-halls
    @PostMapping
    public CinemaHall addMovie(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        return cinemaHallService.add(cinemaHallMapper.toCinemaHall(cinemaHallRequestDto));
    }
    //Get all cinema halls - GET: /cinema-halls
}
