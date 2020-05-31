package vn.elca.training.tdd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.tdd.dto.CompanyGroupDTO;
import vn.elca.training.tdd.service.ICompanyGroupService;

import java.util.List;

@RestController
@RequestMapping("api/v1/company-groups")
public class CompanyGroupController {

    @Autowired
    private ICompanyGroupService companyGroupService;
    
    @GetMapping("")
    public ResponseEntity<List<CompanyGroupDTO>> findAll() {
        return companyGroupService.queryByIdAndGroupLeaderVisa();
    }

}
