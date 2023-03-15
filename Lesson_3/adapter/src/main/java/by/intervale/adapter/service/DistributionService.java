package by.intervale.adapter.service;

import by.intervale.adapter.model.Response;
import by.intervale.adapter.model.request.RequestInfo;

public interface DistributionService {
    Response requestToSMEV(RequestInfo requestInfo);
}
