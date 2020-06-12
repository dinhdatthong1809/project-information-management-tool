package vn.elca.training.tdd.repository.custom.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import vn.elca.training.tdd.dom.Project;

import java.util.List;

public interface IProjectRepositoryCustom {
    
    Page<Project> findSortedChunkInPageWithKeywordAndStatus(String keyword, String status, Pageable pageable, Sort.Order order);
    
    void deleteByIds(List<Project> projects);
    
    boolean isExistedByProjectNumber(Integer projectNumber);
    
}
