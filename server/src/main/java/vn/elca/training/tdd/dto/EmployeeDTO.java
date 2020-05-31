package vn.elca.training.tdd.dto;

import lombok.Getter;
import vn.elca.training.tdd.dom.Employee;

@Getter
public class EmployeeDTO {
    
    private Long id;
    
    private String visa;
    
    private String name;
    
    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.visa = employee.getVisa();
        this.name = employee.getLastName() + " " + employee.getFirstName();
    }
    
}
