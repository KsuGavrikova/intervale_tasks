package by.intervale.supportService.service;

import by.intervale.supportService.model.SupportRequest;
import by.intervale.supportService.model.helper.Adviser;
import by.intervale.supportService.model.helper.Helper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
@Primary
public class SupportServiceMap implements SupportService {

    private final Map<String, Helper> map;


    public SupportServiceMap(List<Helper> helpers) {
        map = helpers.stream().collect(toMap(Helper::myType, Function.identity()));
    }

    @Override
    public void processRequest(SupportRequest supportRequest) {
        System.out.println("Выбор осуществляется с помощью map");
        Helper helper = map.getOrDefault(supportRequest.getType(), new Adviser());
        helper.help(supportRequest);
    }
}
