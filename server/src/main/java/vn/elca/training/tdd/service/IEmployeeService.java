package vn.elca.training.tdd.service;

import org.springframework.http.ResponseEntity;
import vn.elca.training.tdd.dto.EmployeeDTO;

import java.util.List;

public interface IEmployeeService {
    
    ResponseEntity<List<EmployeeDTO>> findAllWithIdAndVisaAndFirstNameAndlastName();
    
}
