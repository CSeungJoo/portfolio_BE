package kr.cseungjoo.commonmodule.security.auth;

import kr.cseungjoo.commonmodule.security.auth.PrincipalDetails;

public interface UserDetailsService {
    PrincipalDetails loadUserByUsername(String username);
}

