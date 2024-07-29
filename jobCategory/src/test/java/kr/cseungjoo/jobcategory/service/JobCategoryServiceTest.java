package kr.cseungjoo.jobcategory.service;

import kr.cseungjoo.jobcategory.domain.JobCategory;
import kr.cseungjoo.jobcategory.exception.JobCategoryNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobCategoryServiceTest {
    @Autowired
    private JobCategoryService jobCategoryService;

    @Test
    void getJobCategoryById() {
        Assertions.assertThatThrownBy(() -> jobCategoryService.getJobCategoryById(2))
                .isInstanceOf(JobCategoryNotFoundException.class);
    }
}