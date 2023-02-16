package by.intervale.smev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    Long id;

    @Column(name = "uuid_request")
    UUID uuidRequest;

    @Column(name = "accrued_sum")
    Long accruedSum;

    @Column(name = "pay_sum")
    Long paySum;

    @Column(name = "resolution_number")
    Integer resolutionNumber;

    @Column(name = "sts_inn")
    String stsInn;

    @Column(name = "resolution_date")
    LocalDate resolutionDate;

    @Column(name = "article_koap")
    String articleKoAP;
}
