package vn.elca.training.tdd.dom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@BatchSize(size = 20)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends AbstractEntity {
    
    @NotNull
    @Column(unique = true)
    @Size(max = 3)
    private String visa;
    
    @NotNull
    @Size(max = 50)
    private String firstName;
    
    @NotNull
    @Size(max = 50)
    private String lastName;
    
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private List<ProjectEmployee> projectEmployees = new ArrayList<>();
    
    public Employee(Long id, String visa, String firstName, String lastName) {
        this.id = id;
        this.visa = visa;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
}
