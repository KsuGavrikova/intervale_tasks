package by.intervale.supportService.model.helper;

import by.intervale.supportService.model.SupportRequest;
import org.springframework.stereotype.Component;

@Component
public class Editor implements Helper {
    @Override
    public void help(SupportRequest supportRequest) {
        System.out.println("Передали запрос специалисту...");
        System.out.println("Редактор зашел на сайт. Нашел ошибку " + supportRequest.getDescription() + ". Исправил.");
    }

    @Override
    public String myType() {
        return Helper.SITE;
    }
}
