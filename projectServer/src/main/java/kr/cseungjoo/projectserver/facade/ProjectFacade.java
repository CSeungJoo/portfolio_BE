package kr.cseungjoo.projectserver.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.cseungjoo.commonmodule.Role;
import kr.cseungjoo.commonmodule.basic.dto.UserIdDto;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.security.jwt.provider.JwtProvider;
import kr.cseungjoo.projectserver.domain.Project;
import kr.cseungjoo.projectserver.dto.CreateProjectDto;
import kr.cseungjoo.projectserver.dto.ModifyProjectDto;
import kr.cseungjoo.projectserver.exception.ProjectNotFoundOrAccessDeniedException;
import kr.cseungjoo.projectserver.feign.UserFeignClient;
import kr.cseungjoo.projectserver.model.ProjectModel;
import kr.cseungjoo.projectserver.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectFacade {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final ProjectService projectService;
    private final UserFeignClient userFeignClient;


    @Transactional
    public ProjectModel create(CreateProjectDto createProjectDto, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Project project = projectService.create(createProjectDto.getTitle(),
                createProjectDto.getSummary(),
                createProjectDto.getDescription(),
                createProjectDto.getTechStack(),
                createProjectDto.getStatus(),
                createProjectDto.getStartAt(),
                createProjectDto.getEndAt(),
                createProjectDto.getGithub(),
                createProjectDto.getProd(),
                createProjectDto.getImageUrl(),
                userIdDto.getUserId()
        );

        ProjectModel projectModel = new ProjectModel(project);

        return projectModel;
    }

    @Transactional
    public ProjectModel modify(ModifyProjectDto modifyProjectDto, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        boolean userMemberOfProject = projectService.isUserMemberOfProject(modifyProjectDto.getProjectId(), userIdDto.getUserId());

        if(!userMemberOfProject) {
            throw new ProjectNotFoundOrAccessDeniedException();
        }

        Project project = projectService.modify(
                modifyProjectDto.getProjectId(),
                modifyProjectDto.getTitle(),
                modifyProjectDto.getSummary(),
                modifyProjectDto.getDescription(),
                modifyProjectDto.getTechStack(),
                modifyProjectDto.getStatus(),
                modifyProjectDto.getStartAt(),
                modifyProjectDto.getEndAt(),
                modifyProjectDto.getGithub(),
                modifyProjectDto.getProd(),
                modifyProjectDto.getImageUrl()
        );

        ProjectModel projectModel = new ProjectModel(project);

        return projectModel;
    }

    @Transactional
    public void remove(long projectId , String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        boolean userMemberOfProject = projectService.isUserMemberOfProject(projectId, userIdDto.getUserId());

        if(!userMemberOfProject) {
            throw new ProjectNotFoundOrAccessDeniedException();
        }

        projectService.remove(projectId);
    }

    public List<ProjectModel> findAll(String nickname) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.nicknameToUserId(systemToken, nickname);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        List<Project> projectList = projectService.findAllByUserId(userIdDto.getUserId());

        List<ProjectModel> projectModelList = projectList.stream()
                .map(ProjectModel::new)
                .toList();

        return projectModelList;
    }

    @Transactional
    public ProjectModel reorder(long projectId, long afterId, String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Project project = projectService.find(projectId, userIdDto.getUserId())
                .orElseThrow(ProjectNotFoundOrAccessDeniedException::new);

        Project reorderedProject = projectService.reorder(project, afterId, userIdDto.getUserId());

        ProjectModel projectModel = new ProjectModel(reorderedProject);

        return projectModel;
    }
}
