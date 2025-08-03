package kr.cseungjoo.profileserver.service;

import kr.cseungjoo.profileserver.domain.Profile;
import kr.cseungjoo.profileserver.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile create(String imgUrl, String name, String engName, String role, String email, String phoneNumber, String github, String blog, Long userId) {
        Profile build = Profile.builder()
                .imgUrl(imgUrl)
                .name(name)
                .engName(engName)
                .role(role)
                .email(email)
                .phoneNumber(phoneNumber)
                .github(github)
                .blog(blog)
                .userId(userId)
                .build();

        Profile save = profileRepository.save(build);

        return save;
    }

    public Optional<Profile> findById(long portfolioId) {
        Optional<Profile> optProfile = profileRepository.findById(portfolioId);

        return optProfile;
    }

    public Optional<Profile> findByUserId(long userId) {
        Optional<Profile> optProfile = profileRepository.findByUserId(userId);

        return optProfile;
    }

    public boolean existsByUserId(long userId) {
        boolean exists = profileRepository.existsByUserId(userId);

        return exists;
    }

    public Profile save(Profile profile) {
        Profile save = profileRepository.save(profile);

        return save;
    }

    public void removeByUserId(long userId) {
        profileRepository.deleteById(userId);
    }
}