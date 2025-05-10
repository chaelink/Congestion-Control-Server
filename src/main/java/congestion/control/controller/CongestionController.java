package congestion.control.controller;

import congestion.control.service.CongestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CongestionController {

    private final CongestionService congestionService; //필드 주입?

    @PostMapping("/message")
    public ResponseEntity<String> submitMessage() {
        congestionService.addMessage("사용자 메세지 요청");
        return ResponseEntity.ok("요청 접수 완료");
    }

}
