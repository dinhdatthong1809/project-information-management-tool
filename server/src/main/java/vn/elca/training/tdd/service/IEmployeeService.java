package vn.elca.training.tdd.service;

import org.springframework.http.ResponseEntity;
import vn.elca.training.tdd.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {
    
    ResponseEntity<List<EmployeeDto>> findAllWithIdAndVisaAndFirstNameAndlastName();
    
}
