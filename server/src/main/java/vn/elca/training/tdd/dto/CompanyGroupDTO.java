package vn.elca.training.tdd.dto;

import lombok.Getter;
import vn.elca.training.tdd.dom.CompanyGroup;

@Getter
public class CompanyGroupDTO {
    
    private Long id;
    
    private String name;
    
    public CompanyGroupDTO(CompanyGroup companyGroup) {
        this.id = companyGroup.getId();
        this.name = companyGroup.getGroupLeader().getVisa();
    }

}
