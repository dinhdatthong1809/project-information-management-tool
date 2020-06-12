package vn.elca.training.tdd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.tdd.dto.EmployeeDto;
import vn.elca.training.tdd.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return employeeService.findAllWithIdAndVisaAndFirstNameAndlastName();
    }
    
}
