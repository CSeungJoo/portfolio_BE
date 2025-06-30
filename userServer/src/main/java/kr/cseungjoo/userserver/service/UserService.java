package kr.cseungjoo.userserver.service;

import kr.cseungjoo.commonmodule.Role;
import kr.cseungjoo.userserver.domain.User;
import kr.cseungjoo.userserver.exception.UserNotFoundException;
import kr.cseungjoo.userserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder pwdEncoder;

    @Transactional
    public User create(User user) {
        User save = userRepository.save(user);

        return save;
    }

    @Transactional
    public User create(String email, String password, String name, LocalDate birth, int careerYears, String phoneNumber, int age) {
        User build = User.builder()
                .email(email)
                .password(pwdEncoder.encode(password))
                .name(name)
                .birth(birth)
                .careerYears(careerYears)
                .phoneNumber(phoneNumber)
                .age(age)
                .role(Role.USER)
                .build();

        User save = userRepository.save(build);

        return save;
    }

    public boolean exist(String email) {
        boolean exist = userRepository.existsByEmail(email);

        return exist;
    }

    public User get(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                UserNotFoundException::new
        );

        return user;
    }


    public User get(String email, String password) {
        User user = get(email);

        if (!pwdEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException();
        }

        return user;
    }
}
