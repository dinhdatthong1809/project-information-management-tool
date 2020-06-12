package vn.elca.training.tdd.dom;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@BatchSize(size = 20)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEmployee {
    
    @EmbeddedId
    private ProjectEmployeeId projectEmployeeId;
    
    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectEmployeeId implements Serializable {
    
        private Long projectId;
    
        private Long employeeId;
    
    }
    
}
