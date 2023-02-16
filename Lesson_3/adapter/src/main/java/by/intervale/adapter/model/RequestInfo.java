package by.intervale.adapter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestInfo {
    UUID uuid;
    String request;
}
