package vic.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vic.cinema.dto.ShoppingCartResponseDto;
import vic.cinema.mapper.ShoppingCartMapper;
import vic.cinema.service.MovieSessionService;
import vic.cinema.service.ShoppingCartService;
import vic.cinema.service.UserService;

@RestController
@RequestMapping("shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartMapper shoppingCartMapper,
                                  ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
    }

    @PostMapping("movie-sessions")
    public void addSession(@RequestParam Long userId,
                           @RequestParam Long movieSessionId) {
        shoppingCartService.addSession(movieSessionService.get(movieSessionId),
                userService.get(userId));
    }

    @GetMapping
    public ShoppingCartResponseDto getShoppingCartByUser(@RequestParam Long userId) {
        return shoppingCartMapper.toDto(shoppingCartService.getByUserId(userId));
    }
}
