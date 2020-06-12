package vn.elca.training.tdd.web.exception;

import org.springframework.http.ResponseEntity;
import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.helpers.TranslateHelper;

public interface IExceptionHandler {
    
    default ResponseEntity<Object> buildResponseEntity(ApiResponse apiResponse) {
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
    
    default String getI18nMessage(I18nMessages i18nMessages) {
        return TranslateHelper.toLocale(i18nMessages);
    }
    
}
