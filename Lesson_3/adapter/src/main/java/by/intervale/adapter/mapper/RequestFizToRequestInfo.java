package by.intervale.adapter.mapper;

import by.intervale.adapter.model.RequestFiz;
import by.intervale.adapter.model.RequestInfo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestFizToRequestInfo {
    public RequestInfo map(RequestFiz requestFiz) {
        return new RequestInfo(UUID.randomUUID(), requestFiz.getSts());
    }
}
