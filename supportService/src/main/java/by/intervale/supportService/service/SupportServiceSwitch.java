package by.intervale.supportService.service;

import by.intervale.supportService.model.SupportRequest;
import by.intervale.supportService.model.helper.Adviser;
import by.intervale.supportService.model.helper.Editor;
import by.intervale.supportService.model.helper.Helper;
import by.intervale.supportService.model.helper.Technic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportServiceSwitch implements SupportService {
    @Autowired
    private Editor editor;
    @Autowired
    private Technic technic;
    @Autowired
    private Adviser adviser;

    @Override
    public void processRequest(SupportRequest supportRequest) {
        System.out.println("Выбор осуществляется с помощью switch-case");
        switch (supportRequest.getType()) {
            case Helper.SITE: {
                editor.help(supportRequest);
            }
            break;
            case Helper.TECHNICAL: {
                technic.help(supportRequest);
            }
            break;
            default: {
                adviser.help(supportRequest);
            }
        }
    }
}
