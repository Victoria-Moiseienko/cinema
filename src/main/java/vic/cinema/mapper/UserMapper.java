package vic.cinema.mapper;

import org.springframework.stereotype.Component;
import vic.cinema.dto.UserResponseDto;
import vic.cinema.model.User;

@Component
public class UserMapper {

    public UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }
}
