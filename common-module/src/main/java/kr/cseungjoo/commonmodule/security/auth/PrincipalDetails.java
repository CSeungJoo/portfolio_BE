package kr.cseungjoo.commonmodule.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class PrincipalDetails implements UserDetails {

    private final String token;
//    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public PrincipalDetails(String token, Collection<String> roles) {
        this.token = token;
        this.authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());;
    }

    @Override
    public String getUsername() {
        return token;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
