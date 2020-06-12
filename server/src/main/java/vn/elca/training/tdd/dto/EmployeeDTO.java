package vn.elca.training.tdd.dto;

import lombok.Data;
import vn.elca.training.tdd.dom.Employee;

@Data
public class EmployeeDto {
    
    private Long id;
    
    private String visa;
    
    private String name;
    
    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.visa = employee.getVisa();
        this.name = employee.getLastName() + " " + employee.getFirstName();
    }
    
}
