package vn.elca.training.tdd.repository.custom.companygroup;

import vn.elca.training.tdd.dom.CompanyGroup;

import java.util.List;

public interface ICompanyGroupRepositoryCustom {

    List<CompanyGroup> findAllWithIdAndGroupLeaderVisa();

}
