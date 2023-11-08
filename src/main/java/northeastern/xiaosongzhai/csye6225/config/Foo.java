package northeastern.xiaosongzhai.csye6225.config;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/6 16:57
 * @Description: stats client
 */
@Component
public class Foo {
    private final StatsDClient statsd;

    @Autowired
    public Foo(StatsDClient statsd) {
        this.statsd = statsd;
    }

    public void recordMetrics() {
        statsd.incrementCounter("bar");
        statsd.recordGaugeValue("baz", 100);
        statsd.recordExecutionTime("bag", 25);
        statsd.recordSetEvent("qux", "one");
    }
}
