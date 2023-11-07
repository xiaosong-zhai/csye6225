package northeastern.xiaosongzhai.csye6225.config;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/11/6 16:57
 * @Description: stats client
 */
public class Foo {
    private static final StatsDClient statsd = new NonBlockingStatsDClient("webapp", "webapp", 8125);

    public static final void main(String[] args) {
        statsd.incrementCounter("bar");
        statsd.recordGaugeValue("baz", 100);
        statsd.recordExecutionTime("bag", 25);
        statsd.recordSetEvent("qux", "one");
    }
}
