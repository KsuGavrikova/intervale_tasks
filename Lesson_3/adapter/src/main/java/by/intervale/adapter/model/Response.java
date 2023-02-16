package by.intervale.adapter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    UUID uuid;
    Long accruedSum;
    Long paySum;
    Integer resolutionNumber;
    String sts;
    LocalDate resolutionDate;
    String articleKoAP;
}
