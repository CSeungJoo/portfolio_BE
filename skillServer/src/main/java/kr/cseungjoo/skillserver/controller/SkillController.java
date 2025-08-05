package kr.cseungjoo.skillserver.controller;

import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.basic.util.BasicUtil;
import kr.cseungjoo.commonmodule.security.auth.PrincipalDetails;
import kr.cseungjoo.skillserver.dto.CreateSkillDto;
import kr.cseungjoo.skillserver.dto.EditSkillDto;
import kr.cseungjoo.skillserver.dto.ReturnSkillDto;
import kr.cseungjoo.skillserver.facade.SkillFacade;
import kr.cseungjoo.skillserver.model.SkillModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillFacade skillFacade;

    @PostMapping("/create")
    public ResponseEntity<BasicResponse.BaseResponse> create(@RequestBody CreateSkillDto createSkillDto) {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();

        SkillModel skillModel = skillFacade.create(createSkillDto, principalDetails.getEmail());

        ReturnSkillDto returnSkillDto = new ReturnSkillDto(skillModel);

        return BasicResponse.ok(returnSkillDto);
    }

    @GetMapping("/{nickname}/info")
    public ResponseEntity<BasicResponse.BaseResponse> findAll(@PathVariable("nickname") String nickname) {
        List<SkillModel> skillModelList = skillFacade.findAll(nickname);

        List<ReturnSkillDto> returnSkillDtoList = skillModelList.stream()
                .map(ReturnSkillDto::new)
                .toList();

        return BasicResponse.ok(returnSkillDtoList);
    }

    @PostMapping("/edit")
    public ResponseEntity<BasicResponse.BaseResponse> edit(@RequestBody EditSkillDto editSkillDto) {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();

        SkillModel edit = skillFacade.edit(editSkillDto, principalDetails.getEmail());

        ReturnSkillDto returnSkillDto = new ReturnSkillDto(edit);

        return BasicResponse.ok(returnSkillDto);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<BasicResponse.BaseResponse> remove(@PathVariable("id") long skillId) {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();

        skillFacade.remove(skillId, principalDetails.getEmail());

        return BasicResponse.ok("ok");
    }
}
