package by.intervale.adapter.service;

import by.intervale.adapter.mapper.RequestFizToRequestInfo;
import by.intervale.adapter.model.request.RequestFiz;
import by.intervale.adapter.model.request.RequestInfo;
import by.intervale.adapter.utils.PropertyResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class DistributionServiceTest {
    private MockWebServer mockWebServer;

    private DistributionServiceImpl distributionService;

    @BeforeEach
    void setupMockWebServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8074);
        distributionService = new DistributionServiceImpl(WebClient.builder(), new PropertyResponse());
    }

    @Test
    void someRestCall() throws InterruptedException, IOException {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(202)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("\"feda45b1-7a8f-4f63-974a-97c09c855ef4\"")
                         );
        RequestFiz requestFiz = new RequestFiz("hello");
        RequestFizToRequestInfo requestFizToRequestInfo = new RequestFizToRequestInfo();
        RequestInfo requestInfo = requestFizToRequestInfo.map(requestFiz);
        distributionService.someRestCall(requestInfo);
        RecordedRequest request = mockWebServer.takeRequest();

        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/smev/request");
        System.out.println(request.getBody());

        mockWebServer.shutdown();
    }

    @Test
    void someRestCallXml() throws InterruptedException {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(202)
//                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                        .setBody("\"feda45b1-7a8f-4f63-974a-97c09c855ef4\"")
        );
        RequestFiz requestFiz = new RequestFiz("hello");
        RequestFizToRequestInfo requestFizToRequestInfo = new RequestFizToRequestInfo();
        RequestInfo requestInfo = requestFizToRequestInfo.map(requestFiz);

        distributionService.someRestCall(requestInfo);

        RecordedRequest request = mockWebServer.takeRequest();

//        assertThat(request.getMethod()).isEqualTo("POST");
//        assertThat(request.getPath()).isEqualTo("/request");
//        assertEquals("application/xml; charset=UTF-8", request.getHeader("Content-Type"));
    }
}