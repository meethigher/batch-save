package config;

import java.util.concurrent.*;

/**
 * 线程池配置
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2022/5/14 13:58
 */
public class ExecutorConfig {

    public ExecutorService executorService() {
       return new ThreadPoolExecutor(
                5,
                10,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()
        );
    }
}
