package kr.cseungjoo.userserver.dto;

import kr.cseungjoo.userserver.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnUserDto {
    private String email;
    private String name;
    private LocalDate birth;
    private int careerYears;
    private String phoneNumber;
    private int age;

    public ReturnUserDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.careerYears = user.getCareerYears();
        this.phoneNumber = user.getPhoneNumber();
        this.age = user.getAge();
    }
}
