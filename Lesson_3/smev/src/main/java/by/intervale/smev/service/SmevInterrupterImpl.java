package by.intervale.smev.service;

import by.intervale.smev.exception.NoEntityException;
import by.intervale.smev.model.RequestInfo;
import by.intervale.smev.model.Response;
import by.intervale.smev.repository.RequestInfoRepository;
import by.intervale.smev.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmevInterrupterImpl implements SmevInterrupter {
    private final RequestInfoRepository requestInfoRepository;
    private final ResponseRepository responseRepository;

    public UUID addRequestToQueue(RequestInfo requestInfo) {
        log.info("Save request " + requestInfo);
        RequestInfo savedRequestInfo = requestInfoRepository.save(requestInfo);
        return savedRequestInfo.getUuid();
    }

    public Response getResponse(RequestInfo requestInfo) {
        return responseRepository.findById(requestInfo.getUuid())
                .orElseThrow(() -> new NoEntityException(requestInfo.toString()));
    }

    public void deleteResponse(UUID uuidResponse) {
        Response response = responseRepository.getReferenceById(uuidResponse);
        responseRepository.delete(response);
    }

}
