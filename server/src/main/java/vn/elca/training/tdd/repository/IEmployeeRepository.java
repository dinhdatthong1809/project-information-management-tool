package vn.elca.training.tdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.elca.training.tdd.dom.Employee;
import vn.elca.training.tdd.repository.custom.employee.IEmployeeRepositoryCustom;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long>, IEmployeeRepositoryCustom {

}
