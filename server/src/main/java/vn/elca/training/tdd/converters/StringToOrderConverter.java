package vn.elca.training.tdd.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class StringToOrderConverter implements Converter<String, Sort.Order> {
    
    private final String SOURCE_PATTERN = "^[a-zA-Z]{1,20}\\.(asc|desc)$";
    
    @Override
    public Sort.Order convert(String source) {
        if (!source.matches(SOURCE_PATTERN)) {
            throw new IllegalArgumentException("Failed to parse [" + source + "] into org.springframework.data.domain.Sort.Order, [" + source + "] does not match SOURCE_PATTERN");
        }
        
        String[] parts = source.split("\\.");
    
        String sortProperty = parts[0];
        String direction = parts[1];
        
        return new Sort.Order(Sort.Direction.fromString(direction), sortProperty);
    }
    
}
