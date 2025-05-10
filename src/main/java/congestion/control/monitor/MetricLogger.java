package congestion.control.monitor;

import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;

@Component
public class MetricLogger {
    private final BufferedWriter writer;

    public MetricLogger() throws IOException {
        writer = new BufferedWriter(new FileWriter("metrics.csv", true));
        writer.write("timestamp,queueSize,delayMs,elapsedTimeMs\n");
    }

    public synchronized void logMetric(int queueSize, int delayMs, long elapsedTime) {
        try {
            writer.write(String.format("%s,%d,%d,%d\n",
                    LocalDateTime.now(), queueSize, delayMs, elapsedTime));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
