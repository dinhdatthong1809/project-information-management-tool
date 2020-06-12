package vn.elca.training.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.tdd.dto.EmployeeDto;
import vn.elca.training.tdd.repository.IEmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {
    
    @Autowired
    private IEmployeeRepository employeeRepository;
    
    @Override
    public ResponseEntity<List<EmployeeDto>> findAllWithIdAndVisaAndFirstNameAndlastName() {
        // @formatter:off
        List<EmployeeDto> employeeDtos = employeeRepository.findAllWithIdAndVisaAndFirstNameAndlastName()
                                                           .stream()
                                                           .map(EmployeeDto::new)
                                                           .collect(Collectors.toList());
        // @formatter:on
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }
    
}
