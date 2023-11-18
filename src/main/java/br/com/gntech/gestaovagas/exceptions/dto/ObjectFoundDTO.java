package br.com.gntech.gestaovagas.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ObjectFoundDTO {

    private String message;
    private HttpStatus httpStatus;
    private List<String> path;
}
