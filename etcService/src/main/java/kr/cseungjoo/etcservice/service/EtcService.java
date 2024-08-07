package kr.cseungjoo.etcservice.service;

import kr.cseungjoo.etcservice.domain.Etc;
import kr.cseungjoo.etcservice.repository.EtcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtcService {
    private final EtcRepository etcRepository;

    public List<Etc> getEtcListByUserId(long userId) {
        return etcRepository.getAllByUsersId(userId);
    }
}
