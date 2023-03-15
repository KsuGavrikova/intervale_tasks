package by.intervale.adapter.model.request;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestInfo {
    UUID uuid;
    String request;
}
