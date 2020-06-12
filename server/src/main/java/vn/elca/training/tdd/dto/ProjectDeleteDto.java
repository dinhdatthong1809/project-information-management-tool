package vn.elca.training.tdd.dto;

import lombok.Data;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.enums.ProjectStatus;

@Data
public class ProjectDeleteDto extends AbstractVersionDto {
    
    private long id;
    
    private int projectNumber;
    
    private ProjectStatus status;
    
    public Project getProject() {
        Project project = new Project();
        
        project.setId(this.id);
        project.setProjectNumber(this.projectNumber);
        project.setStatus(this.status);
        project.setVersion(this.version);
        
        return project;
    }
    
}
