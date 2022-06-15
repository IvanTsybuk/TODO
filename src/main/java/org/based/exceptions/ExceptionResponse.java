package org.based.exceptions;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.web.context.request.WebRequest;

@Data
public class ExceptionResponse {
    private String message;
    private WebRequest request;
    private LocalDateTime timestamp;
    public ExceptionResponse() {
    }
    public ExceptionResponse(String message, WebRequest request, LocalDateTime timestamp) {
        this.message = message;
        this.request = request;
        this.timestamp = timestamp;
    }
}
