package br.com.gntech.gestaovagas.exceptions;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(String message) {
        super(message);
    }
}
