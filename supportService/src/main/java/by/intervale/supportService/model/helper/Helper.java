package by.intervale.supportService.model.helper;

import by.intervale.supportService.model.SupportRequest;

public interface Helper {
    String TECHNICAL = "technical";
    String SITE = "site";

    void help(SupportRequest supportRequest);

    String myType();

}
