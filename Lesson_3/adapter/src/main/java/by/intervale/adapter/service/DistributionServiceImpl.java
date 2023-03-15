package by.intervale.adapter.service;

import by.intervale.adapter.model.Response;
import by.intervale.adapter.model.request.RequestInfo;
import by.intervale.adapter.utils.PropertyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistributionServiceImpl implements DistributionService {
    private final WebClient.Builder webClient;
    private final PropertyResponse property;


    private WebClient builderWebClient() {
        return this.webClient.baseUrl(property.getUri())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Response requestToSMEV(RequestInfo requestInfo) {
        webClient.baseUrl(property.getUri())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        log.info("get request with UUID" + requestInfo.getUuid() + " and for client" + requestInfo.getRequest());
        someRestCall(requestInfo);
        log.info("request transferred");
        log.info("get info by request");
        Response response = responseCall(requestInfo).block();
        log.info("response received");
        responseDelete(response.getUuid());
        log.info("response deleted");
        return response;
    }

    public UUID someRestCall(RequestInfo requestInfo) {
        return builderWebClient()
                .post()
                .uri("/request")
                .bodyValue(requestInfo)
                .retrieve()
                .bodyToMono(UUID.class)
                .block();
    }

    public Mono<Response> responseCall(RequestInfo requestInfo) {
        return builderWebClient()
                .post()
                .uri("/check/")
                .bodyValue(requestInfo)
                .retrieve()
                .bodyToMono(Response.class)
                .retryWhen(Retry.fixedDelay(property.getMaxAttempts(), Duration.ofMillis(property.getDelayAttempts())));
    }

    public void responseDelete(UUID uuidResponse) {
        builderWebClient()
                .method(HttpMethod.DELETE)
                .uri("/delete")
                .body(Mono.just(uuidResponse), UUID.class)
                .retrieve();
    }

}
