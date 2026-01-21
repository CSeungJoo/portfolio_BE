package kr.cseungjoo.projectserver.repository;

import kr.cseungjoo.projectserver.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUserIdOrderBySortOrderAsc(long userId);

    boolean existsByIdAndUserId(long projectId, long userId);

    Optional<Project> findByIdAndUserId(Long id, long userId);

    @Query("SELECT COALESCE(MAX(p.sortOrder), 0) FROM Project p WHERE p.userId = :userId")
    long findMaxSortOrderByUserId(@Param("userId") long userId);

    @Modifying
    @Query("UPDATE Project p SET p.sortOrder = p.sortOrder + 1 WHERE p.userId = :userId AND p.sortOrder >= :fromOrder")
    void incrementSortOrderFrom(@Param("userId") long userId, @Param("fromOrder") long fromOrder);
}
