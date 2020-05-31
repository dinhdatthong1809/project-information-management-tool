package vn.elca.training.tdd.web.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.exception.ProjectNumberAlreadyExistsException;
import vn.elca.training.tdd.helpers.TranslateHelper;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    private ResponseEntity<Object> buildResponseEntity(ApiResponse apiResponse) {
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
    
    private String getI18nMessage(I18nMessages i18nMessages) {
        return TranslateHelper.toLocale(i18nMessages.getKeyLanguage());
    }
    
    // @formatter:off
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(
                                      HttpStatus.INTERNAL_SERVER_ERROR,
                                      getI18nMessage(I18nMessages.MALFORMED_JSON_REQUEST)
                                  );
        
        return buildResponseEntity(apiResponse);
    }
    
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String apiNotFound = getI18nMessage(I18nMessages.API_NOT_FOUND);
        ApiResponse apiResponse = new ApiResponse(
                                      HttpStatus.NOT_FOUND,
                                      apiNotFound
                                  );
        
        return buildResponseEntity(apiResponse);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse(
                                      HttpStatus.NOT_FOUND,
                                      ex.getMessage()
                                  );
        
        return buildResponseEntity(apiResponse);
    }
    
    @ExceptionHandler(ProjectNumberAlreadyExistsException.class)
    protected ResponseEntity<Object> handleProjectNumberIsAlreadyExistedException(ProjectNumberAlreadyExistsException ex) {
        ApiResponse apiResponse = new ApiResponse(
                                      HttpStatus.UNPROCESSABLE_ENTITY,
                                      ex.getMessage()
                                  );
        
        return buildResponseEntity(apiResponse);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder message = new StringBuilder();
        
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            if (!violation.getMessage().isEmpty()) {
                message.append(violation.getMessage().concat("\n"));
            }
        }
        
        ApiResponse apiResponse = new ApiResponse(
                                      HttpStatus.BAD_REQUEST,
                                      message.toString()
                                  );
        
        return buildResponseEntity(apiResponse);
    }
    
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    protected ResponseEntity<Object> handleObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        ApiResponse apiResponse = new ApiResponse(
                                      HttpStatus.CONFLICT,
                                      getI18nMessage(I18nMessages.THIS_PROJECT_WAS_UPDATED_BY_AN_OTHER_USER_PLEASE_REFRESH_PAGE)
                                  );
    
        ex.printStackTrace();
        
        return buildResponseEntity(apiResponse);
    }
    
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        ApiResponse apiResponse = new ApiResponse(
                                      HttpStatus.INTERNAL_SERVER_ERROR,
                                      getI18nMessage(I18nMessages.SOMETHING_BAD_HAPPENED)
                                  );
        
        return buildResponseEntity(apiResponse);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ApiResponse apiResponse = new ApiResponse(
                                      HttpStatus.NOT_FOUND,
                                      getI18nMessage(I18nMessages.API_NOT_FOUND)
                                  );
        
        return buildResponseEntity(apiResponse);
    }
    
    // @formatter:on
    
}
