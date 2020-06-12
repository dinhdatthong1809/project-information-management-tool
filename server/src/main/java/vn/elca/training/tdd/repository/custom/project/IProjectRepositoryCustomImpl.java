package vn.elca.training.tdd.repository.custom.project;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.QProject;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.exception.project.ProjectConcurrentUpdateException;
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
    public Page<Project> findSortedChunkInPageWithKeywordAndStatus(String keyword, String status, Pageable pageable, Sort.Order order) {
    
        BooleanExpression whereClausePredicate;
        
        if (NumberHelper.isInteger(keyword)) {
            whereClausePredicate = project.projectNumber.eq(Integer.parseInt(keyword));
        } else {
            whereClausePredicate = project.name.containsIgnoreCase(keyword)
                                   .or(project.customer.containsIgnoreCase(keyword));
        }
    
        whereClausePredicate = whereClausePredicate
                               .and(
                                   (status == null) || ("".equals(status)) ? null
                                                                           : project.status.stringValue().eq(status)
                               );
        
        PathBuilder orderByExpression = new PathBuilder(Project.class, "project");
        
        OrderSpecifier orderSpecifier = new OrderSpecifier(
            order.isAscending() ? Order.ASC : Order.DESC,
            orderByExpression.get(order.getProperty())
        );
        
        JPAQuery<Project> projectQuery = new JPAQueryFactory(entityManager)
                                             .select(Projections.fields(Project.class,
                                                 project.id,
                                                 project.projectNumber,
                                                 project.name,
                                                 project.status,
                                                 project.customer,
                                                 project.startDate,
                                                 project.version
                                             ))
                                             .from(project)
                                             .where(
                                                 whereClausePredicate
                                             )
                                             .orderBy(orderSpecifier);
        
        long totalProject = projectQuery.fetchCount();
        
        List<Project> projects = projectQuery
                                 .offset(pageable.getOffset())
                                 .limit(pageable.getPageSize())
                                 .fetch();
        
        return new PageImpl<>(projects, pageable, totalProject);
    }
    
    @Override
    public void deleteByIds(List<Project> projects) {
        for (Project project : projects) {
            Project projectInDatabase = new JPAQueryFactory(entityManager)
                                            .selectFrom(this.project)
                                            .where(this.project.id.eq(project.getId()))
                                            .fetchFirst();
            
            if (project.getVersion() != projectInDatabase.getVersion()) {
                throw new ProjectConcurrentUpdateException(project.getProjectNumber());
            }
            
            projectInDatabase.getProjectEmployees().clear();
            
            new JPAQueryFactory(entityManager)
                .delete(this.project)
                .where(
                    this.project.id.in(projectInDatabase.getId())
                    .and(this.project.status.eq(ProjectStatus.NEW)))
                .execute();
        }
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
