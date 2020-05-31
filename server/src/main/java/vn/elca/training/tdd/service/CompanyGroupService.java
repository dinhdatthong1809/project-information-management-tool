package vn.elca.training.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.tdd.dto.CompanyGroupDTO;
import vn.elca.training.tdd.repository.ICompanyGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyGroupService implements ICompanyGroupService {
    
    @Autowired
    private ICompanyGroupRepository companyGroupRepository;
    
    @Override
    public ResponseEntity<List<CompanyGroupDTO>> queryByIdAndGroupLeaderVisa() {
        
        // @formatter:off
        List<CompanyGroupDTO> companyGroupDTOS = companyGroupRepository.findAllWithIdAndGroupLeaderVisa()
                                                                       .stream()
                                                                       .map(CompanyGroupDTO::new)
                                                                       .collect(Collectors.toList());
        // @formatter:on
        
        return new ResponseEntity<>(companyGroupDTOS, HttpStatus.OK);
    }
    
}
