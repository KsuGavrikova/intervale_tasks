package by.intervale.smev.controller;

import by.intervale.smev.model.RequestInfo;
import by.intervale.smev.model.Response;
import by.intervale.smev.service.SmevInterrupter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/smev/")
public class SmevRequestController {
    public final SmevInterrupter smevService;

    @PostMapping("request")
    public UUID requestInfo(@RequestBody RequestInfo requestInfo) {
        log.info("method requestInfo post request " + requestInfo);
        smevService.addRequestToQueue(requestInfo);
        return requestInfo.getUuid();
//        return requestInfo.getRequest() + " request add to queue";
    }

    @PostMapping("check")
    public Response getResponse(@RequestBody RequestInfo requestInfo) {
        log.info("method getResponse get request " + requestInfo);
        return smevService.getResponse(requestInfo);
    }

    @DeleteMapping("response-delete")
    public void deleteRequestInfo(@RequestBody UUID uuidResponse) {
        log.info("method deleteResponse get uuid Response " + uuidResponse);
        smevService.deleteResponse(uuidResponse);
    }
}
