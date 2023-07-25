package cinema.buisnesslayer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TheSeatIsTakenException.class, WrongSeatIndexException.class,
            WrongTokenException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({WrongPasswordException.class})
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(RuntimeException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
