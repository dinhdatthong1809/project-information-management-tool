package vn.elca.training.tdd.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.tdd.web.exception.ApiResponse;

@RestController
public class ApplicationController {
    
    @RequestMapping("")
    public ResponseEntity<ApiResponse> index() {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "Welcome to ELCA");
        
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
    
}
