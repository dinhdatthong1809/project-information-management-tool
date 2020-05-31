package vn.elca.training.tdd.repository.custom.companygroup;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import vn.elca.training.tdd.dom.CompanyGroup;
import vn.elca.training.tdd.dom.QCompanyGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ICompanyGroupRepositoryCustomImpl implements ICompanyGroupRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private QCompanyGroup companyGroup = QCompanyGroup.companyGroup;
    
    @Override
    public List<CompanyGroup> findAllWithIdAndGroupLeaderVisa() {
        // @formatter:off
        return new JPAQueryFactory(entityManager)
                   .select(Projections.constructor(CompanyGroup.class,
                       companyGroup.id,
                       companyGroup.groupLeader.visa
                   ))
                   .from(companyGroup)
                   .fetch();
        // @formatter:on
    }
    
}
