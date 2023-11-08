package northeastern.xiaosongzhai.csye6225.aspect;

import com.timgroup.statsd.StatsDClient;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/8 10:15
 * @Description: aspect for statsd
 */
@Aspect
@Component
public class StatsDAspect {

    private final StatsDClient statsDClient;

    public StatsDAspect(StatsDClient statsDClient) {
        this.statsDClient = statsDClient;
    }

    @Before("execution(* northeastern.xiaosongzhai.csye6225.controller.*.*(..))")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        statsDClient.incrementCounter(methodName + ".called");
    }
}
