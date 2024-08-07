package kr.cseungjoo.profileservice.service;

import kr.cseungjoo.profileservice.domain.Profile;
import kr.cseungjoo.profileservice.exception.ProfileNotFoundException;
import kr.cseungjoo.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile getProfileByUsersId(long users_id) {
        return profileRepository.findById(users_id).orElseThrow(ProfileNotFoundException::new);
    }

    public Profile createProfileByUsersId(long users_id, String imgUrl, String introduce, List<Integer> jobCategory_id, List<Integer> techStack_id) {
        Profile profile = new Profile(imgUrl, introduce, users_id, jobCategory_id, techStack_id);
        return profileRepository.save(profile);
    }
}
