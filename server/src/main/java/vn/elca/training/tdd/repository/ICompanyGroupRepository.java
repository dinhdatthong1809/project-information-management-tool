package vn.elca.training.tdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.elca.training.tdd.dom.CompanyGroup;
import vn.elca.training.tdd.repository.custom.companygroup.ICompanyGroupRepositoryCustom;

@Repository
public interface ICompanyGroupRepository extends JpaRepository<CompanyGroup, Long>, ICompanyGroupRepositoryCustom {

}
