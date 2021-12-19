package magenta.distancecalculator.exception;

import magenta.distancecalculator.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionImpl extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleDataNotFoundException(DataNotFoundException dataNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO(dataNotFoundException.getMessage()));
    }
}