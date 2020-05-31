package vn.elca.training.tdd.repository.custom.employee;

import vn.elca.training.tdd.dom.Employee;

import java.util.List;

public interface IEmployeeRepositoryCustom {

    List<Employee> findAllWithIdAndVisaAndFirstNameAndlastName();

}
