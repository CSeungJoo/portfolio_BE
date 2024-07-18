package kr.cseungjoo.jobcategory.service;

import kr.cseungjoo.jobcategory.domain.JobCategory;
import kr.cseungjoo.jobcategory.repository.JobCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    public List<JobCategory> getJobCategoryByIds(Iterable<Integer> ids) {
        return jobCategoryRepository.findAllById(ids);
    }
}
