package by.intervale.smev.service;

import by.intervale.smev.model.RequestInfo;
import by.intervale.smev.model.Response;
import by.intervale.smev.repository.RequestInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SmevService {
    private final RequestInfoRepository requestInfoRepository;
    private final MyThread workerThread;

    public void addRequestToQueue(RequestInfo requestInfo) {
        requestInfoRepository.save(requestInfo);
    }

    public Response getResponse(RequestInfo requestInfo) {
        return new Response();
    }

    public void workWithWorker() {
        try {
            workerThread.start();
            log.info("thread " + workerThread.getName() + " have status " + workerThread.getState());
//            ii.	Эмулировать взаимодействие с ГИСГМП
            workerThread.join(200);
            log.info("thread " + workerThread.getName() + " have status " + workerThread.getState());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void delete(RequestInfo requestInfo) {
        requestInfoRepository.delete(requestInfo);
    }

}
