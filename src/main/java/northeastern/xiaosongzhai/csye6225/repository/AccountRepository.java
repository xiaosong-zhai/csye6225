package northeastern.xiaosongzhai.csye6225.repository;

import northeastern.xiaosongzhai.csye6225.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/1 16:49
 * @Description: Account repository
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);

}
