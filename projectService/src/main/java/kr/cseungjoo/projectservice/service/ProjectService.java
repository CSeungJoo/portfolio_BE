package kr.cseungjoo.projectservice.service;

import kr.cseungjoo.projectservice.domain.Project;
import kr.cseungjoo.projectservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> getProjectsByUser(long users_id) {
        return projectRepository.getAllByUsers_id(users_id);
    }
}
