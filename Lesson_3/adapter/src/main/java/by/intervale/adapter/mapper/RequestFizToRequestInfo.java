package by.intervale.adapter.mapper;

import by.intervale.adapter.model.request.RequestFiz;
import by.intervale.adapter.model.request.RequestInfo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestFizToRequestInfo {
    public RequestInfo map(RequestFiz requestFiz) {
        return new RequestInfo(UUID.randomUUID(), requestFiz.getSts());
    }
}
