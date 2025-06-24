package br.com.vinisantosdev.gerenciamentopacientes.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {
    private HttpStatus statusCode;
    private String codeMessage;
    private String errorMessage;

}