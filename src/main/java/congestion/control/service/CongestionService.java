package congestion.control.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class CongestionService {

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final ExecutorService executer = Executors.newFixedThreadPool(5);


}
