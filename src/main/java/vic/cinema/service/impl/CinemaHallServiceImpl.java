package vic.cinema.service.impl;

import java.util.List;
import vic.cinema.dao.CinemaHallDao;
import vic.cinema.lib.Inject;
import vic.cinema.lib.Service;
import vic.cinema.model.CinemaHall;
import vic.cinema.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
