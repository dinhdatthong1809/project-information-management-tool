package vn.elca.training.tdd.dom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;
import vn.elca.training.tdd.constants.DateFormat;

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
    @Size(min = 1, max = 3)
    private String visa;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;
    
    @NotNull
    @DateTimeFormat(pattern = DateFormat.APP_PATTERN)
    private LocalDate birthDate;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employeeId")
    private List<ProjectEmployee> projectEmployees = new ArrayList<>();
    
}
