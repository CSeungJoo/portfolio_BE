package kr.cseungjoo.skillserver.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.cseungjoo.commonmodule.Role;
import kr.cseungjoo.commonmodule.basic.dto.UserIdDto;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.security.jwt.provider.JwtProvider;
import kr.cseungjoo.skillserver.domain.Skill;
import kr.cseungjoo.skillserver.dto.CreateSkillDto;
import kr.cseungjoo.skillserver.dto.EditSkillDto;
import kr.cseungjoo.skillserver.exception.SkillNotOwnerException;
import kr.cseungjoo.skillserver.feign.UserFeignClient;
import kr.cseungjoo.skillserver.model.SkillModel;
import kr.cseungjoo.skillserver.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillFacade {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final SkillService skillService;
    private final UserFeignClient userFeignClient;

    public SkillModel create(CreateSkillDto createSkillDto, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Skill skill = skillService.create(createSkillDto.getName(), createSkillDto.getProficiencyRate(), createSkillDto.getSkillLevel(), createSkillDto.getImgUrl(), userIdDto.getUserId());

        SkillModel skillModel = new SkillModel(skill);

        return skillModel;
    }

    public List<SkillModel> findAll(String nickname) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.nicknameToUserId(systemToken, nickname);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        List<Skill> skillList = skillService.findAll(userIdDto.getUserId());

        List<SkillModel> skillModelList = skillList.stream()
                .map(SkillModel::new)
                .toList();

        return skillModelList;
    }

    public SkillModel edit(EditSkillDto editSkillDto, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Skill skill = skillService.getById(editSkillDto.getSkillId());

        if(skill.getUserId() != userIdDto.getUserId()) {
            throw new SkillNotOwnerException();
        }

        skill.modify(editSkillDto.getName(), editSkillDto.getProficiencyRate(), editSkillDto.getSkillLevel(), editSkillDto.getImgUrl());

        Skill save = skillService.save(skill);

        SkillModel skillModel = new SkillModel(save);

        return skillModel;
    }

    public void remove(long skillId, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Skill skill = skillService.getById(skillId);

        if(skill.getUserId() != userIdDto.getUserId()) {
            throw new SkillNotOwnerException();
        }

        skillService.remove(skill);
    }
}
