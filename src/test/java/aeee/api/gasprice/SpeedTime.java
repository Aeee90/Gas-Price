package aeee.api.gasprice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.function.Function;

@Slf4j
public class SpeedTime {
    public static long measure(String task, Function<Void, Void> process){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        process.apply(null);
        stopWatch.stop();
        log.info("{}: {}", task, stopWatch.getTotalTimeMillis());
        return stopWatch.getTotalTimeMillis();
    }
}
