package kr.cseungjoo.awardserver.controller;

import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.basic.util.BasicUtil;
import kr.cseungjoo.commonmodule.security.auth.PrincipalDetails;
import kr.cseungjoo.awardserver.dto.CreateAwardDto;
import kr.cseungjoo.awardserver.dto.EditAwardDto;
import kr.cseungjoo.awardserver.dto.ReturnAwardDto;
import kr.cseungjoo.awardserver.facade.AwardFacade;
import kr.cseungjoo.awardserver.model.AwardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/award")
@RequiredArgsConstructor
public class AwardController {

    private final AwardFacade awardFacade;

    @PostMapping("/create")
    public ResponseEntity<BasicResponse.BaseResponse> create(@RequestBody CreateAwardDto createAwardDto) {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();

        AwardModel awardModel = awardFacade.create(createAwardDto, principalDetails.getEmail());

        ReturnAwardDto returnAwardDto = new ReturnAwardDto(awardModel);

        return BasicResponse.ok(returnAwardDto);
    }

    @GetMapping("/{nickname}/info")
    public ResponseEntity<BasicResponse.BaseResponse> findAll(@PathVariable("nickname") String nickname) {
        List<AwardModel> awardModelList = awardFacade.findAll(nickname);

        List<ReturnAwardDto> returnAwardDtoList = awardModelList.stream()
                .map(ReturnAwardDto::new)
                .toList();

        return BasicResponse.ok(returnAwardDtoList);
    }

    @PostMapping("/edit")
    public ResponseEntity<BasicResponse.BaseResponse> edit(@RequestBody EditAwardDto editAwardDto) {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();

        AwardModel edit = awardFacade.edit(editAwardDto, principalDetails.getEmail());

        ReturnAwardDto returnAwardDto = new ReturnAwardDto(edit);

        return BasicResponse.ok(returnAwardDto);
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<BasicResponse.BaseResponse> remove(@PathVariable("id") long awardId) {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();

        awardFacade.remove(awardId, principalDetails.getEmail());

        return BasicResponse.ok("ok");
    }
}