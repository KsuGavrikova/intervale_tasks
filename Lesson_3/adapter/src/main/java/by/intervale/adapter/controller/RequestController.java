package by.intervale.adapter.controller;

import by.intervale.adapter.mapper.RequestFizToRequestInfo;
import by.intervale.adapter.mapper.RequestUrToRequestInfo;
import by.intervale.adapter.model.Response;
import by.intervale.adapter.model.request.RequestFiz;
import by.intervale.adapter.model.request.RequestInfo;
import by.intervale.adapter.model.request.RequestUr;
import by.intervale.adapter.service.DistributionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/adapter/")
public class RequestController {

    private final DistributionService myService;

    @PostMapping("request-fiz")
    public ResponseEntity<Response> requestFiz(@Valid @RequestBody RequestFiz requestFiz) {
        log.info("request-fiz get request" + requestFiz);
        var requestFizToRequestInfo = new RequestFizToRequestInfo();
        RequestInfo requestInfo = requestFizToRequestInfo.map(requestFiz);
        Response response = myService.requestToSMEV(requestInfo);
        log.info("request-fiz transmits response " + "");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("request-ur")
    public ResponseEntity<Response> requestUr(@Valid @RequestBody RequestUr requestUr) {
        log.info("request-ur get request" + requestUr);
        var requestUrToRequestInfo = new RequestUrToRequestInfo();
        RequestInfo requestInfo = requestUrToRequestInfo.map(requestUr);
        Response response = myService.requestToSMEV(requestInfo);
        log.info("request-ur transmits response " + "");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
