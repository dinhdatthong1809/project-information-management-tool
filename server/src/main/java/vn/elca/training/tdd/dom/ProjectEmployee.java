package vn.elca.training.tdd.dom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

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
