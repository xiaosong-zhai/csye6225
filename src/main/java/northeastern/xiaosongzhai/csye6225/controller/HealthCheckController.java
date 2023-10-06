package northeastern.xiaosongzhai.csye6225.controller;

import jakarta.servlet.http.HttpServletRequest;
import northeastern.xiaosongzhai.csye6225.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author: Xiaosong Zhai
 * @date: 2023/9/26 19:37
 * @Description: the controller of checking the health of the application
 */
@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;


    @RequestMapping("/healthz")
    public ResponseEntity<String> healthCheck(HttpServletRequest request) {
        String requestMethod = request.getMethod();
        return healthCheckService.healthCheck(requestMethod);
    }
}


