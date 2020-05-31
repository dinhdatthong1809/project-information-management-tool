package vn.elca.training.tdd.dto;

import lombok.Getter;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.enums.ProjectStatus;

import java.time.LocalDate;

@Getter
public class ProjectTableRowDTO {
    
    private long id;
    
    private int projectNumber;
    
    private String name;
    
    private ProjectStatus status;
    
    private String customer;
    
    private LocalDate startDate;
    
    public ProjectTableRowDTO(Project project) {
        this.id = project.getId();
        this.projectNumber = project.getProjectNumber();
        this.name = project.getName();
        this.status = project.getStatus();
        this.customer = project.getCustomer();
        this.startDate = project.getStartDate();
    }
    
}
