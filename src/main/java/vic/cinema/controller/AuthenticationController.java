package vic.cinema.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vic.cinema.dto.UserRequestDto;
import vic.cinema.security.AuthenticationService;

@RestController
public class AuthenticationController {
    //Register - POST: /register
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody UserRequestDto requestDto) {
        service.register(requestDto.getEmail(), requestDto.getPassword());
    }
}
