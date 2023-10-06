package northeastern.xiaosongzhai.csye6225.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/9/27 11:51
 * @Description: the service of checking the health of the application
 */
public interface HealthCheckService {

    ResponseEntity<String> healthCheck(String requestMethod);
}
