package vn.elca.training.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.tdd.dto.CompanyGroupDto;
import vn.elca.training.tdd.repository.ICompanyGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyGroupService implements ICompanyGroupService {
    
    @Autowired
    private ICompanyGroupRepository companyGroupRepository;
    
    @Override
    public ResponseEntity<List<CompanyGroupDto>> queryByIdAndGroupLeaderVisa() {
        
        // @formatter:off
        List<CompanyGroupDto> companyGroupDtos = companyGroupRepository.findAllWithIdAndGroupLeaderVisa()
                                                                       .stream()
                                                                       .map(CompanyGroupDto::new)
                                                                       .collect(Collectors.toList());
        // @formatter:on
        
        return new ResponseEntity<>(companyGroupDtos, HttpStatus.OK);
    }
    
}
