package by.intervale.adapter.mapper;

import by.intervale.adapter.model.RequestInfo;
import by.intervale.adapter.model.RequestUr;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestUrToRequestInfo {
    public RequestInfo map(RequestUr requestUr) {
        return new RequestInfo(UUID.randomUUID(), requestUr.getInn());
    }
}
