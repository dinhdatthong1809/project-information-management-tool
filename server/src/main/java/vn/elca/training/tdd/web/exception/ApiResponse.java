package vn.elca.training.tdd.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponse {
    
    private HttpStatus status;
    
    private String message;
    
}
