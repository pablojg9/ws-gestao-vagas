package br.com.gntech.gestaovagas.exceptions.handlers;

import br.com.gntech.gestaovagas.exceptions.ObjectFoundException;
import br.com.gntech.gestaovagas.exceptions.dto.ErrorMessageDTO;
import br.com.gntech.gestaovagas.exceptions.dto.ObjectFoundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestControllerAdvice
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    @Autowired
    private ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ErrorMessageDTO> dto = new ArrayList<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            dto.add(new ErrorMessageDTO(message, err.getField()));
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectFoundException.class)
    public ResponseEntity<ObjectFoundDTO> handleUserFoundException(ObjectFoundException userFoundException) {

        ObjectFoundDTO userFoundDTO = new ObjectFoundDTO(userFoundException.getMessage(),
                HttpStatus.BAD_REQUEST,
                Arrays.stream(userFoundException.getStackTrace()).toList().get(7).getClassName());

        return new ResponseEntity<>(userFoundDTO, HttpStatus.BAD_REQUEST);
    }
}
