package vn.elca.training.tdd.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TranslateHelper {
    
    private static ResourceBundleMessageSource messageSource;
    
    @Autowired
    TranslateHelper(ResourceBundleMessageSource messageSource) {
        TranslateHelper.messageSource = messageSource;
    }
    
    public static String toLocale(String i18nMessages) {
        Locale locale = LocaleContextHolder.getLocale();
        
        return messageSource.getMessage(i18nMessages, null, locale);
    }
    
}
