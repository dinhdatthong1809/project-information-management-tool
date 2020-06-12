package vn.elca.training.tdd.repository.custom.employee;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import vn.elca.training.tdd.dom.Employee;
import vn.elca.training.tdd.dom.QEmployee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class IEmployeeRepositoryCustomImpl implements IEmployeeRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private QEmployee employee = QEmployee.employee;
    
    @Override
    public List<Employee> findAllWithIdAndVisaAndFirstNameAndlastName() {
        // @formatter:off
        return new JPAQueryFactory(entityManager)
                   .select(Projections.fields(Employee.class,
                       employee.id,
                       employee.visa,
                       employee.firstName,
                       employee.lastName
                   ))
                   .from(employee)
                   .fetch();
        // @formatter:on
    }
    
}
