package vn.elca.training.tdd.dom;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;
import vn.elca.training.tdd.abstracts.Sortable;
import vn.elca.training.tdd.constants.DateFormat;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.dom.validator.project.ProjectEndDateValid;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@BatchSize(size = 20)
@ProjectEndDateValid

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project extends AbstractEntity implements Sortable {
    
    @NotNull
    private Long companyGroupId;
    
    @Column(unique = true)
    @NotNull
    private Integer projectNumber;
    
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name;
    
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String customer;
    
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;
    
    @NotNull
    @DateTimeFormat(pattern = DateFormat.APP_PATTERN)
    @JsonFormat(pattern = DateFormat.APP_PATTERN)
    private LocalDate startDate;
    
    @DateTimeFormat(pattern = DateFormat.APP_PATTERN)
    @JsonFormat(pattern = DateFormat.APP_PATTERN)
    private LocalDate endDate;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "projectId")
    private List<ProjectEmployee> projectEmployees = new ArrayList<>();
    
    @Override
    public String[] getSortableFields() {
        return new String[] {"projectNumber", "name", "status", "customer", "startDate"};
    }
    
}
