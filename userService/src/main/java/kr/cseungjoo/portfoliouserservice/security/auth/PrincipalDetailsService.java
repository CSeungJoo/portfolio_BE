package kr.cseungjoo.portfoliouserservice.security.auth;

import kr.cseungjoo.portfoliouserservice.domain.User;
import kr.cseungjoo.portfoliouserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.getUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("존재하지 않은 유저이름입니다.")
        );

        return new PrincipalDetails(user);
    }
}
