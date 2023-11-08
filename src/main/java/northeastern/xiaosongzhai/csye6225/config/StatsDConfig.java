package northeastern.xiaosongzhai.csye6225.config;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/8 10:23
 * @Description: statsd config
 */
@Configuration
public class StatsDConfig {

    @Bean
    public StatsDClient statsDClient() {
        // Configure a NonBlockingStatsDClient
        return new NonBlockingStatsDClient("webapp", "localhost", 8125);
    }

}
