package kr.cseungjoo.jobcategory.repository;

import kr.cseungjoo.jobcategory.domain.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer> {
}
