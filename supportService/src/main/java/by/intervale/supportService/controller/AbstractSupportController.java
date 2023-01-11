package by.intervale.supportService.controller;

import by.intervale.supportService.model.SupportRequest;
import by.intervale.supportService.service.SupportService;

public abstract class AbstractSupportController {
    protected SupportService support;

    abstract SupportRequest treat(SupportRequest supportRequest);
}
