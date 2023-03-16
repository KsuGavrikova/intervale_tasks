package by.intervale.smev.service;

import by.intervale.smev.model.RequestInfo;
import by.intervale.smev.model.Response;
import by.intervale.smev.model.ResponseGisgmp;
import by.intervale.smev.repository.GisgmpRepository;
import by.intervale.smev.repository.RequestInfoRepository;
import by.intervale.smev.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class Worker implements Runnable {
    private final RequestInfoRepository requestInfoRepository;
    private final GisgmpRepository gisgmpRepository;
    private final ResponseRepository responseRepository;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private boolean startThread = true;

    @PostConstruct
    public void start() {
        log.info("Worker started");
        executorService.submit(new Worker(requestInfoRepository, gisgmpRepository, responseRepository));
    }

    @Override
    public void run() {
        while (startThread) {
            log.info("Subtracts from a persistent chain of connections to get information");
            List<RequestInfo> messageList = getRequestFromQueue();

            for (RequestInfo requestFromQueue : messageList) {
                log.info("get request " + requestFromQueue);

                log.info("Emulate interaction with GISMP");
                ResponseGisgmp gimpResponse = emulator(requestFromQueue);

                log.info("Push response to persistent response queue");
                responseToQueue(mapResponseGisgmpToResponse(requestFromQueue, gimpResponse));

                log.info("Remove a request from the request queue");
                deleteRequestFromQueue(requestFromQueue);
            }

            try {
                Thread.currentThread().join(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private Response mapResponseGisgmpToResponse(RequestInfo requestFromQueue, ResponseGisgmp gimpResponse) {
        return new Response(
                requestFromQueue.getUuid(),
                gimpResponse.getAccruedSum(),
                gimpResponse.getPaySum(),
                gimpResponse.getResolutionNumber(),
                gimpResponse.getStsInn(),
                gimpResponse.getResolutionDate(),
                gimpResponse.getArticleKoAP()
        );
    }

    private List<RequestInfo> getRequestFromQueue() {
        return requestInfoRepository.findAll();
    }

    public ResponseGisgmp emulator(RequestInfo requestFromQueue) {
        ResponseGisgmp gimpResponse = gisgmpRepository.getByStsInn(requestFromQueue.getRequest()).orElse(new ResponseGisgmp());
        log.info("get gimpResponse " + gimpResponse);
        return gimpResponse;
    }

    private void responseToQueue(Response response) {
        responseRepository.save(response);
        log.info("save response " + response);
    }

    private void deleteRequestFromQueue(RequestInfo requestFromQueue) {
        requestInfoRepository.delete(requestFromQueue);
        log.info("delete request " + requestFromQueue);
    }

}
