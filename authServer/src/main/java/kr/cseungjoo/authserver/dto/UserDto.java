package kr.cseungjoo.authserver.dto;

import kr.cseungjoo.commonmodule.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDate birth;
    private int careerYears;
    private String phoneNumber;
    private int age;
    private Role role;
}
