package exceptionhandler.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionResponse {
    private String message;
}
