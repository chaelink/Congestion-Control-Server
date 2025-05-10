package congestion.control.service;

import congestion.control.monitor.MetricLogger;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class CongestionService {

    private final MetricLogger metricLogger;
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final ExecutorService executer = Executors.newFixedThreadPool(5);
    private volatile int delay = 100;

    public CongestionService(MetricLogger metricLogger) {
        this.metricLogger = metricLogger;
    }

    @PostConstruct
    public void initWorkers() {
        for (int i = 0; i < 5; i++) {
            executer.submit(() -> {
                while (true) {
                    try {
                        String message = queue.take();
                        long start = System.currentTimeMillis();

                        Thread.sleep(100); //처리량 지연 작업(속도 조절)
                        long elapsed = System.currentTimeMillis() - start;

                        System.out.println("처리 : "+ message);
                        metricLogger.logMetric(queue.size(), delay, elapsed);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    public void addMessage(String message) {
        queue.add(message);
    }
    public int getQueueSize() {
        return queue.size();
    }
    public int getDelay() {
        return delay;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }

}
