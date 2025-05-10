package congestion.control.monitor;

import congestion.control.service.CongestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CongestionMonitor {

    private final CongestionService congestionService;
    private int delay = 100;

    @Scheduled(fixedRate = 1000)
    public void monitor() {
        int queueSize = congestionService.getQueueSize();
        int currentDelay = congestionService.getDelay();

        if(queueSize > 50) {
            int newDelay = (int) (currentDelay * 1.5); //혼잡 시 처리 속도 늦춤
            congestionService.setDelay(newDelay);
            System.out.println("⚠ 혼잡 감지! 큐 크기: " + queueSize + " → delay 증가: " + newDelay + "ms");
        } else if(queueSize < 10 && currentDelay>50) {
            int newDelay = Math.max(50, currentDelay - 10);
            congestionService.setDelay(newDelay);
            System.out.println("✅ 회복 중. 큐 크기: " + queueSize + " → delay 감소: " + newDelay + "ms");
        } else {
            System.out.println("📊 큐 상태 안정. 크기: " + queueSize + " | delay: " + currentDelay + "ms");
        }

    }

}
