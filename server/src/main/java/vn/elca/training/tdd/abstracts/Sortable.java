package vn.elca.training.tdd.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;
import java.util.Arrays;

public interface Sortable {
    
    @Transient
    @JsonIgnore
    String[] getSortableFields();
    
    default boolean isSortable(String field) {
        return Arrays.stream(getSortableFields())
                     .anyMatch(field::equals);
    }
    
}
