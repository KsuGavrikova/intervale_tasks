package by.intervale.supportService.model.helper;

import by.intervale.supportService.model.SupportRequest;
import org.springframework.stereotype.Component;

@Component
public class Technic implements Helper {

    @Override
    public void help(SupportRequest supportRequest) {
        System.out.println("Передали запрос специалисту...");
        System.out.println("Техник чинит ... " + supportRequest.getDescription() + ". Починил.");

    }

    @Override
    public String myType() {
        return Helper.TECHNICAL;
    }
}
