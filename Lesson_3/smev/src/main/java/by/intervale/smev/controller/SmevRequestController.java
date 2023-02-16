package by.intervale.smev.controller;

import by.intervale.smev.model.RequestInfo;
import by.intervale.smev.model.Response;
import by.intervale.smev.service.SmevService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * a.	Принять запрос на получение информации
 * b.	Сохранить запрос в персистентную очередь(табл. В БД)
 * d.	Отдать ответ по запросу за ответом
 * e.	Удалить ответ из очереди при получении запроса подтверждения(Acknowledge)
 **/

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/smev/")
public class SmevRequestController {
    public final SmevService smevService;
    
    @PostMapping("request")
    public String requestInfo(@RequestBody RequestInfo requestInfo) {
        log.info("method requestInfo get request " + requestInfo);
        smevService.addRequestToQueue(requestInfo);
        return requestInfo.getRequest() + " request add to queue";
    }

    @GetMapping("worker")
    public void workerTest() {
        log.info("hello from workerTest");
        smevService.workWithWorker();
    }

    @GetMapping("check")
    public Response getResponse(@RequestBody RequestInfo requestInfo) {
        log.info("method getResponse get request " + requestInfo);
        return smevService.getResponse(requestInfo);
    }

    @DeleteMapping("response-delete")
    public void deleteRequestInfo(@RequestBody RequestInfo requestInfo) {
        log.info("method deleteResponse get request " + requestInfo);
        smevService.delete(requestInfo);
    }
}
