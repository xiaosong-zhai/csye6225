package northeastern.xiaosongzhai.csye6225.repository;

import jakarta.transaction.Transactional;
import northeastern.xiaosongzhai.csye6225.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/1 16:50
 * @Description: Assignment repository
 */
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {

    @Transactional
    void deleteAssignmentById(String id);
}
