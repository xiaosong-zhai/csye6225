package northeastern.xiaosongzhai.csye6225.aspect;

import com.timgroup.statsd.StatsDClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/8 10:15
 * @Description: aspect for statsd
 */
@Aspect
@Component
public class StatsDAspect {

    private final ConcurrentHashMap<String, Integer> apiCallCounts = new ConcurrentHashMap<>();

    private static final Logger logger = LogManager.getLogger(StatsDAspect.class);

    private final StatsDClient statsDClient;

    public StatsDAspect(StatsDClient statsDClient) {
        this.statsDClient = statsDClient;
    }

    @Before("execution(* northeastern.xiaosongzhai.csye6225.controller.*.*(..))")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        statsDClient.incrementCounter(methodName + ".called");

        int currentCount = apiCallCounts.compute(methodName, (key, val) -> (val == null) ? 1 : val + 1);
        logger.info("API Method " + methodName + " called, count: " + currentCount);
    }
}
