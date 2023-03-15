package by.intervale.adapter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.ConnectException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> entityNotFoundException(ConnectException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<Violation> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

}