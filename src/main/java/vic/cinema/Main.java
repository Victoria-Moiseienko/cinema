package vic.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.security.sasl.AuthenticationException;

import org.apache.log4j.Logger;
import vic.cinema.lib.Injector;
import vic.cinema.model.CinemaHall;
import vic.cinema.model.Movie;
import vic.cinema.model.MovieSession;
import vic.cinema.model.User;
import vic.cinema.sequrity.AuthenticationService;
import vic.cinema.service.CinemaHallService;
import vic.cinema.service.MovieService;
import vic.cinema.service.MovieSessionService;
import vic.cinema.service.OrderService;
import vic.cinema.service.ShoppingCartService;

public class Main {
    private static Injector injector = Injector.getInstance("vic.cinema");
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws AuthenticationException {
        logger.debug("--- Movies ---");
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        Movie movie2 = new Movie();
        movie2.setTitle("The Gentlemen");
        movieService.add(movie2);
        movieService.getAll().forEach(logger::debug);

        logger.debug("--- Cinema Halls ---");
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("White");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(logger::debug);

        logger.debug("--- Movie Sessions ---");
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.now().plusDays(3));
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setMovie(movie2);
        movieSession2.setCinemaHall(cinemaHall);
        movieSession2.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession2);

        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(movie2.getId(), LocalDate.now());
        availableSessions.forEach(logger::debug);

        logger.debug("--- Users ---");
        User user = new User();
        user.setEmail("example@email.com");
        user.setPassword("qwerty");
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);

        authenticationService.register(user.getEmail(), user.getPassword());
        User user2 = authenticationService.login(user.getEmail(), user.getPassword());
        logger.debug(user2);

        logger.debug("--- Shopping Cart ---");
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession2, user2);
        shoppingCartService.addSession(movieSession, user2);
        logger.debug("Cart with tickets: ");
        logger.debug(shoppingCartService.getByUser(user2));

        logger.debug("---- Order ----");
        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user2));
        logger.debug("Order History:");
        orderService.getOrderHistory(user2).forEach(logger::debug);
    }
}
