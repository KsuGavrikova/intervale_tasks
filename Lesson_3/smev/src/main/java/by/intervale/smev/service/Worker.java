package by.intervale.smev.service;

import by.intervale.smev.model.RequestInfo;
import by.intervale.smev.model.Response;
import by.intervale.smev.model.ResponseGisgmp;
import by.intervale.smev.repository.GisgmpRepository;
import by.intervale.smev.repository.RequestInfoRepository;
import by.intervale.smev.repository.ResponseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * c.	Реализовать Worker который будет запускаться в отдельном потоке и эмулировать взаимодействие с ГИСГМП
 * i.	Вычитывать сообщения из персистентной очереди запросов на получение информации
 * ii.	Эмулировать взаимодействие с ГИСГМП
 * iii.	Помещать ответ в персистентную очередь ответов
 * iv.	Удалять запрос из очереди запросов
 **/

@Slf4j
@Service
@AllArgsConstructor
public class Worker {
    private final RequestInfoRepository requestInfoRepository;
    private final GisgmpRepository gisgmpRepository;
    private final ResponseRepository responseRepository;

    public List<RequestInfo> getRequestFromQueue() {
        return requestInfoRepository.findAll();
    }

    public void emulator(List<RequestInfo> list) {
        for (RequestInfo r : list) {
            log.info("get request " + r);
//            ResponseGisgmp gimpResponse = gisgmpRepository.getById(1L);

            gisgmpRepository.save(new ResponseGisgmp(1L, 2222L, 2222L, 333,
                    r.getRequest(), LocalDate.now(), "kmlhbjn"));
            ResponseGisgmp gimpResponse = gisgmpRepository.getByStsInn(r.getRequest()).orElse(new ResponseGisgmp());
            log.info("get gimpResponse " + gimpResponse);

            log.info("get gimpResponse ");
            Response response = new Response(
                    1L,
                    r.getUuid(),
                    gimpResponse.getAccruedSum(),
                    gimpResponse.getPaySum(),
                    gimpResponse.getResolutionNumber(),
                    gimpResponse.getStsInn(),
                    gimpResponse.getResolutionDate(),
                    gimpResponse.getArticleKoAP()
            );
            responseRepository.save(response);
            log.info("save response " + response);
            responseRepository.delete(response);
            log.info("delete response " + response);
        }
    }
}
