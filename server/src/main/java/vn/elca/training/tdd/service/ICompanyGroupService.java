package vn.elca.training.tdd.service;

import org.springframework.http.ResponseEntity;
import vn.elca.training.tdd.dto.CompanyGroupDTO;

import java.util.List;

public interface ICompanyGroupService {
    
    ResponseEntity<List<CompanyGroupDTO>> queryByIdAndGroupLeaderVisa();
    
}
