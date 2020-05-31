package vn.elca.training.tdd.dom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@BatchSize(size = 20)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyGroup extends AbstractEntity {
    
    @NotNull
    @OneToOne
    private Employee groupLeader = new Employee();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private List<Project> projects = new ArrayList<>();
    
    public CompanyGroup(Long id, String groupLeaderVisa) {
        this.id = id;
        this.groupLeader.setVisa(groupLeaderVisa);
    }
    
}
