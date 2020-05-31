package vn.elca.training.tdd.repository.custom.project;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.QProject;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.helpers.NumberHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class IProjectRepositoryCustomImpl implements IProjectRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private QProject project = QProject.project;
    
    // @formatter:off
    
    @Override
    public Page<Project> findAChunkInPageWithKeywordAndStatus(String keyword, String status, Pageable pageable) {
    
        // prepare where clause predicate: if keyword is integer, query by projectNumber, else, query by name and customer
        BooleanExpression whereClausePredicate;
        
        if (NumberHelper.isInteger(keyword)) {
            whereClausePredicate = project.projectNumber.eq(Integer.parseInt(keyword));
            
        } else {
            whereClausePredicate = project.name.containsIgnoreCase(keyword)
                                   .or(project.customer.containsIgnoreCase(keyword));
        }
    
        // prepare status predicate: if status is empty, query all status
        Predicate statusPredicate = status.isEmpty() ? null
                                                     : project.status.stringValue().eq(status);
        
        
        // prepare query statement
        JPAQuery<Project> projectQuery = new JPAQueryFactory(entityManager)
                                             .select(Projections.constructor(Project.class,
                                                 project.id,
                                                 project.projectNumber,
                                                 project.name,
                                                 project.status,
                                                 project.customer,
                                                 project.startDate
                                             ))
                                             .from(project)
                                             .where(
                                                 whereClausePredicate
                                                 .and(statusPredicate)
                                             )
                                             .orderBy(project.projectNumber.asc());
        
        long totalProject = projectQuery.fetchCount();
        
        List<Project> projects = projectQuery
                                 .offset(pageable.getOffset())
                                 .limit(pageable.getPageSize())
                                 .fetch();
        
        
        return new PageImpl<>(projects, pageable, totalProject);
    }
    
    @Override
    public void deleteByIds(Long[] ids) {
        new JPAQueryFactory(entityManager)
            .delete(project)
            .where(
                project.id.in(ids)
                .and(project.status.eq(ProjectStatus.NEW)))
            .execute();
    }
    
    @Override
    public boolean isExistedByProjectNumber(Integer projectNumber) {
        boolean isNotExisted = new JPAQueryFactory(entityManager)
                                   .select(Projections.fields(Project.class,
                                       project.projectNumber
                                   ))
                                   .from(project)
                                   .where(project.projectNumber.eq(projectNumber))
                                   .fetchResults()
                                   .isEmpty();
        
        return !isNotExisted;
    }
    
    // @formatter:on
    
}
