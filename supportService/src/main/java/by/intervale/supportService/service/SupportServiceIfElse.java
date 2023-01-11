package by.intervale.supportService.service;

import by.intervale.supportService.model.SupportRequest;
import by.intervale.supportService.model.helper.Adviser;
import by.intervale.supportService.model.helper.Editor;
import by.intervale.supportService.model.helper.Helper;
import by.intervale.supportService.model.helper.Technic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportServiceIfElse implements SupportService {
    @Autowired
    private Editor editor;
    @Autowired
    private Technic technic;
    @Autowired
    private Adviser adviser;

    @Override
    public void processRequest(SupportRequest supportRequest) {
        System.out.println("Выбор осуществляется с помощью if...else");
        if (supportRequest.getType().equals(Helper.SITE)) {
            editor.help(supportRequest);
        } else if (supportRequest.getType().equals(Helper.TECHNICAL)) {
            technic.help(supportRequest);
        } else {
            adviser.help(supportRequest);
        }
    }
}
