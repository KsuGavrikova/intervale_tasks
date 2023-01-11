package by.intervale.supportService.controller;

import by.intervale.supportService.model.SupportRequest;
import by.intervale.supportService.service.SupportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/map/")
public class SupportControllerMap extends AbstractSupportController {
    public SupportControllerMap(SupportService support) {
        this.support = support;
    }

    @PostMapping("treat")
    public SupportRequest treat(@RequestBody SupportRequest supportRequest) {
        support.processRequest(supportRequest);
        return supportRequest;
    }
}
