package vn.elca.training.tdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.elca.training.tdd.dom.ProjectEmployee;

@Repository
public interface IProjectEmployeeRepository extends JpaRepository<ProjectEmployee, ProjectEmployee.ProjectEmployeeId> {

}
