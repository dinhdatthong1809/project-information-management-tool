package vn.elca.training.tdd.repository.custom.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.elca.training.tdd.dom.Project;

public interface IProjectRepositoryCustom {
    
    Page<Project> findAChunkInPageWithKeywordAndStatus(String keyword, String status, Pageable pageable);
    
    void deleteByIds(Long[] ids);
    
    boolean isExistedByProjectNumber(Integer projectNumber);
    
}
