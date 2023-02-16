package by.intervale.adapter.service;

import by.intervale.adapter.mapper.RequestFizToRequestInfo;
import by.intervale.adapter.mapper.RequestUrToRequestInfo;
import by.intervale.adapter.model.RequestFiz;
import by.intervale.adapter.model.RequestInfo;
import by.intervale.adapter.model.RequestUr;
import by.intervale.adapter.model.Response;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

/**
 * 1.
 * Сервис адаптер, который будет принимать сообщения по протоколу HTTP синхронно
 * и далее в асинхронном режиме взаимодействовать со вторым сервисом
 * a.	Получить запрос на штраф от партнера
 * b.	Отправить запрос на получение информации
 * c.	Отправить запрос на получение ответа(Внимание! Ответ может быть получен не с первой попытки)
 * d.	Отправить ответ со штрафом партнеру
 * e.	Отправить подтверждение получение ответа(Acknowledge)
 */

@Service
@AllArgsConstructor
public class MyService {

    private final RequestFizToRequestInfo requestFizToRequestInfo;
    private final RequestUrToRequestInfo requestUrToRequestInfo;
    private final WebClient.Builder builder;

    @Value("${responseColl.uri}")
    private static String responseCollUri;
    @Value("${responseColl.uri}")
    private static int maxAttempts;
    @Value("${responseColl.delayAttempts}")
    private static int delayAttempts;
    @Value("${someRestCall.uri}")
    private static String someRestCallUri;

    public String fizRestCall(RequestFiz requestFiz) {
        RequestInfo requestInfo = requestFizToRequestInfo.map(requestFiz);
        //return ok or exception
        System.out.println(this.someRestCall(requestInfo));
        //return responce
        return someRestCall(requestInfo);
    }

    public String urRestCall(RequestUr requestUr) {
        RequestInfo requestInfo = requestUrToRequestInfo.map(requestUr);
        someRestCall(requestInfo);
        getResponseCall();
        return someRestCall(requestInfo);
    }

    //    b.	Отправить запрос на получение информации
    public String someRestCall(RequestInfo requestInfo) {
        return builder.build().post().uri(someRestCallUri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestInfo), RequestInfo.class)
                .retrieve().bodyToMono(String.class).block();
    }

    //    c.Отправить запрос на получение ответа
    //    (Внимание! Ответ может быть получен не с первой попытки)
    public Response getResponseCall() {
        return builder.build()
                .get()
                .uri(responseCollUri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Response.class)
                .retryWhen(Retry.fixedDelay(maxAttempts, Duration.ofMillis(delayAttempts)))
                .block();
    }

}
