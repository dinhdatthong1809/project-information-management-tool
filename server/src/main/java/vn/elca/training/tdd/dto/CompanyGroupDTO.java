package vn.elca.training.tdd.dto;

import lombok.Data;
import vn.elca.training.tdd.dom.CompanyGroup;

@Data
public class CompanyGroupDto {
    
    private Long id;
    
    private String name;
    
    public CompanyGroupDto(CompanyGroup companyGroup) {
        this.id = companyGroup.getId();
        this.name = companyGroup.getGroupLeader().getVisa();
    }

}
