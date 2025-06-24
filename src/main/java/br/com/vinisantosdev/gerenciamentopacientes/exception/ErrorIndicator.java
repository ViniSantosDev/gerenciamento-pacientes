package br.com.vinisantosdev.gerenciamentopacientes.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
public enum ErrorIndicator {

    ERROR_INDICATOR_001("Patient not found", "ERROR_INDICATOR_001", HttpStatus.NOT_FOUND),
    ERROR_INDICATOR_003("Error Writter in Excel", "ERROR_INDICATOR_003", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String codeMessage;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    ErrorIndicator(String errorMessage, String codeMessage, HttpStatus httpStatus) {
        this.codeMessage = codeMessage;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public CustomError callException(){
        return new CustomError(this.getHttpStatus(), this.getCodeMessage(),this.getErrorMessage());
    }

}
