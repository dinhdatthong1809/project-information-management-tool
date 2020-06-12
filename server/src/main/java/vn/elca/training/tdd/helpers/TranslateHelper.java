package vn.elca.training.tdd.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.dom.enums.ProjectStatus;

import java.util.Locale;

@Component
public class TranslateHelper {
    
    private static ResourceBundleMessageSource messageSource;
    
    @Autowired
    TranslateHelper(ResourceBundleMessageSource messageSource) {
        TranslateHelper.messageSource = messageSource;
    }
    
    public static String toLocale(I18nMessages i18nMessages) {
        Locale locale = LocaleContextHolder.getLocale();
        
        return messageSource.getMessage(i18nMessages.getKeyLanguage(), i18nMessages.getParams(), locale);
    }
    
    public static String toLocale(String message) {
        Locale locale = LocaleContextHolder.getLocale();
        
        return messageSource.getMessage(message, null, locale);
    }
    
}
