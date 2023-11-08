package northeastern.xiaosongzhai.csye6225.service.impl;

import northeastern.xiaosongzhai.csye6225.config.Foo;
import northeastern.xiaosongzhai.csye6225.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/9/27 11:51
 * @Description: implement the service of checking the health of the application
 */
@Service
public class HealthCheckServiceImpl implements HealthCheckService {

    private static final String HEADER_CACHE_CONTROL = "no-cache, no-store, must-revalidate";
    private static final String HEADER_PRAGMA = "no-cache";
    private static final String CONTENT_TYPE_KEY = "X-Content-Type-Options";
    private static final String CONTENT_TYPE_VALUE = "nosniff";


    @Autowired
    private DataSource dataSource;

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(HEADER_CACHE_CONTROL);
        headers.setPragma(HEADER_PRAGMA);
        headers.set(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
        return headers;
    }

    @Override
    public ResponseEntity<String> healthCheck(String requestMethod) {
        HttpHeaders headers = setHeaders();
        if (!requestMethod.equals(HttpMethod.GET.toString())) {
            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .headers(headers).
                    build();
        }
        try{
            Connection connection = dataSource.getConnection();
            if (connection.isValid(2)){
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .headers(headers)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .headers(headers)
                .build();
    }
}
