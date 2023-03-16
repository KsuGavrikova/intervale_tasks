package by.intervale.smev.service;

import by.intervale.smev.model.RequestInfo;
import by.intervale.smev.model.Response;

import java.util.UUID;

public interface SmevInterrupter {
    UUID addRequestToQueue(RequestInfo requestInfo);

    Response getResponse(RequestInfo requestInfo);

    void deleteResponse(UUID uuidResponse);
}
