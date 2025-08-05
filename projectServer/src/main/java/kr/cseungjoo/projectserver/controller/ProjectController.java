package kr.cseungjoo.projectserver.controller;

import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.basic.util.BasicUtil;
import kr.cseungjoo.commonmodule.security.auth.PrincipalDetails;
import kr.cseungjoo.projectserver.dto.CreateProjectDto;
import kr.cseungjoo.projectserver.dto.ModifyProjectDto;
import kr.cseungjoo.projectserver.dto.ReturnProjectDto;
import kr.cseungjoo.projectserver.facade.ProjectFacade;
import kr.cseungjoo.projectserver.model.ProjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectFacade projectFacade;

    @GetMapping("/{nickname}/all")
    public ResponseEntity<BasicResponse.BaseResponse> findAll(@PathVariable("nickname") String nickname) {
        List<ProjectModel> projectModelList = projectFacade.findAll(nickname);

        List<ReturnProjectDto> returnProjectDtoList = projectModelList.stream()
                .map(ReturnProjectDto::new)
                .toList();

        return BasicResponse.ok(returnProjectDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<BasicResponse.BaseResponse> create(@RequestBody CreateProjectDto createProjectDto) {
        PrincipalDetails principal = BasicUtil.getPrincipal();
        ProjectModel projectModel = projectFacade.create(createProjectDto, principal.getEmail());

        ReturnProjectDto returnProjectDto = new ReturnProjectDto(projectModel);

        return BasicResponse.ok(returnProjectDto);
    }

    @PostMapping("/{id}/modify")
    public ResponseEntity<BasicResponse.BaseResponse> modify(@RequestBody ModifyProjectDto modifyProjectDto) {
        PrincipalDetails principal = BasicUtil.getPrincipal();
        ProjectModel projectModel = projectFacade.modify(modifyProjectDto, principal.getEmail());

        ReturnProjectDto returnProjectDto = new ReturnProjectDto(projectModel);

        return BasicResponse.ok(returnProjectDto);
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<BasicResponse.BaseResponse> remove(@PathVariable("id") long projectId) {
        PrincipalDetails principal = BasicUtil.getPrincipal();
        projectFacade.remove(projectId, principal.getEmail());

        return BasicResponse.ok("ok");
    }
}
