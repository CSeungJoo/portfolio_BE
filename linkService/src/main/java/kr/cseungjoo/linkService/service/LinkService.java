package kr.cseungjoo.linkService.service;

import kr.cseungjoo.linkService.domain.Link;
import kr.cseungjoo.linkService.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;

    public List<Link> getLinkListByUsersId(long usersId) {
        return linkRepository.getAllByUsersId(usersId);
    }
}
