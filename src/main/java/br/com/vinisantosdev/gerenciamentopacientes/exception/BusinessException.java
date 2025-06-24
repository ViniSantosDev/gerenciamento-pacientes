package br.com.vinisantosdev.gerenciamentopacientes.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BusinessException extends Exception {

    private final HttpStatus statusCode;
    private final String codeMessage;
    private final String errorMessage;

    public BusinessException(ErrorIndicator customError) {
        this.statusCode = customError.getHttpStatus();
        this.codeMessage = customError.getCodeMessage();
        this.errorMessage = customError.getErrorMessage();
    }
}