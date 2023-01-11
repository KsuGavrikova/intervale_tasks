package by.intervale.supportService.model.helper;

import by.intervale.supportService.model.SupportRequest;
import org.springframework.stereotype.Component;

@Component
public class Adviser implements Helper {

    @Override
    public void help(SupportRequest supportRequest) {
        System.out.println("Если у вас проблемы с сайтом, отправьте запрос типом site.\n" +
                "Ecли у вас проблемы с техникой, отправьте запрос с типом technic.");
    }

    @Override
    public String myType() {
        return null;
    }
}
