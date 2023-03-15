package by.intervale.adapter.model.request;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestFiz {
    @Pattern(regexp = "^\\d{2}\\w{2}\\d{6}$")
    @NotBlank(message = "sts is mandatory")
    String sts;
}
