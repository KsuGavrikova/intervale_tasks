package by.intervale.adapter.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUr {
    @Pattern(regexp = "^\\d{10}$")
    @NotBlank(message = "sts is mandatory")
    String inn;
}
