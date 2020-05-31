package vn.elca.training.tdd.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import vn.elca.training.tdd.constants.Language;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
    
    List<Locale> LOCALES = Arrays.asList(
        new Locale(Language.EN.name().toLowerCase()),
        new Locale(Language.FR.name().toLowerCase())
    );
    
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String changeLangSignal = request.getHeader("Accept-Language");
        
        if ((changeLangSignal == null) || changeLangSignal.isEmpty()) {
            return Locale.getDefault();
        }
        
        return Locale.lookup(Locale.LanguageRange.parse(changeLangSignal), LOCALES);
    }
    
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("i18n/messages");
        resource.setDefaultEncoding("UTF-8");
        resource.setUseCodeAsDefaultMessage(true);
        
        return resource;
    }
    
}
