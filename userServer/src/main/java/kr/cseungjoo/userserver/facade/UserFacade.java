package kr.cseungjoo.userserver.facade;

import kr.cseungjoo.userserver.domain.User;
import kr.cseungjoo.userserver.dto.LoginDto;
import kr.cseungjoo.userserver.dto.RegisterDto;
import kr.cseungjoo.userserver.exception.AlreadyExistUserException;
import kr.cseungjoo.userserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public User register(RegisterDto registerDto) {
        boolean exist = userService.exist(registerDto.getEmail());

        if(exist) {
            throw new AlreadyExistUserException();
        }

        User user = userService.create(
                registerDto.getEmail(),
                registerDto.getPassword(),
                registerDto.getName(),
                registerDto.getNickname(),
                registerDto.getBirth(),
                registerDto.getCareerYears(),
                registerDto.getPhoneNumber(),
                registerDto.getAge()
        );

        return user;
    }

    public User info(String email) {
        User user = userService.get(email);

        return user;
    }

    public User login(LoginDto loginDto) {
        User user = userService.get(loginDto.getEmail(), loginDto.getPassword());

        return user;
    }

    public long getIdByEmail(String email) {
        long userId = userService.getId(email);

        return userId;
    }

    public long getIdByNickname(String nickname) {
        long userId =  userService.getIdByNickname(nickname);

        return userId;
    }
}
