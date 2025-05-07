package congestion.control.controller;

import congestion.control.service.CongestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CongestionController {

    private final CongestionService congestionService; //필드 주입?

}
