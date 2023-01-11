package by.intervale.supportService.controller;

import by.intervale.supportService.model.SupportRequest;
import by.intervale.supportService.service.SupportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/if-else/")
public class SupportControllerIfElse extends AbstractSupportController {
    public SupportControllerIfElse(@Qualifier("supportServiceIfElse") SupportService support) {
        this.support = support;
    }

    @PostMapping("treat")
    public SupportRequest treat(@RequestBody SupportRequest supportRequest) {
        support.processRequest(supportRequest);
        return supportRequest;
    }
}
