package by.intervale.adapter.controller;

import by.intervale.adapter.model.RequestFiz;
import by.intervale.adapter.model.RequestUr;
import by.intervale.adapter.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/adapter/")
public class RequestController {

    private final MyService myService;

    // a.	Получить запрос на штраф от партнера
    @PostMapping("request-fiz")
    public ResponseEntity<HttpStatus> requestFiz(@RequestBody RequestFiz requestFiz) {
        log.info(myService.fizRestCall(requestFiz));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("request-ur")
    public String requestUr(@RequestBody RequestUr requestUr) {
        return myService.urRestCall(requestUr);
    }


}
