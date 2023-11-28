package northeastern.xiaosongzhai.csye6225.repository;

import northeastern.xiaosongzhai.csye6225.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/26 17:55
 * @Description: Submission repository
 */
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {

    @Query("SELECT COUNT(s) FROM Submission s WHERE s.assignment.id = :id and s.assignment.created_by.email = :email")
    Long countSubmissionsByIdAndEmail(String id, String email);

}
