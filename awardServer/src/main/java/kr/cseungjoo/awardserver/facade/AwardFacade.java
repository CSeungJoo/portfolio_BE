package kr.cseungjoo.awardserver.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.cseungjoo.commonmodule.Role;
import kr.cseungjoo.commonmodule.basic.dto.UserIdDto;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.security.jwt.provider.JwtProvider;
import kr.cseungjoo.awardserver.domain.Award;
import kr.cseungjoo.awardserver.dto.CreateAwardDto;
import kr.cseungjoo.awardserver.dto.EditAwardDto;
import kr.cseungjoo.awardserver.exception.AwardNotOwnerException;
import kr.cseungjoo.awardserver.feign.UserFeignClient;
import kr.cseungjoo.awardserver.model.AwardModel;
import kr.cseungjoo.awardserver.service.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardFacade {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final AwardService awardService;
    private final UserFeignClient userFeignClient;

    public AwardModel create(CreateAwardDto createAwardDto, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Award award = awardService.create(createAwardDto.getName(), createAwardDto.getOrganization(), createAwardDto.getAwardRank(), createAwardDto.getAwardedAt(), userIdDto.getUserId());

        AwardModel awardModel = new AwardModel(award);

        return awardModel;
    }

    public List<AwardModel> findAll(String nickname) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.nicknameToUserId(systemToken, nickname);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        List<Award> awardList = awardService.findAll(userIdDto.getUserId());

        List<AwardModel> awardModelList = awardList.stream()
                .map(AwardModel::new)
                .toList();

        return awardModelList;
    }

    public AwardModel edit(EditAwardDto editAwardDto, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Award award = awardService.getById(editAwardDto.getAwardId());

        if(award.getUserId() != userIdDto.getUserId()) {
            throw new AwardNotOwnerException();
        }

        award.modify(editAwardDto.getName(), editAwardDto.getOrganization(), editAwardDto.getAwardRank(), editAwardDto.getAwardedAt());

        Award save = awardService.save(award);

        AwardModel awardModel = new AwardModel(save);

        return awardModel;
    }

    public void remove(long awardId, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Award award = awardService.getById(awardId);

        if(award.getUserId() != userIdDto.getUserId()) {
            throw new AwardNotOwnerException();
        }

        awardService.remove(award);
    }
}