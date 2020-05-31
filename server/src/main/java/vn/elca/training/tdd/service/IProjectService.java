package vn.elca.training.tdd.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.dto.ProjectTableRowDTO;

import java.util.List;

public interface IProjectService {
    
    ResponseEntity<Page<ProjectTableRowDTO>> findAChunkInPageWithKeywordAndStatus(String keyword, String status, int chunk, int page);
    
    ResponseEntity<List<ProjectStatus>> findAllStatuses();
    
    ResponseEntity<Project> findById(Long id);
    
    ResponseEntity<Project> findByProjectNumber(Integer projectNumber);
    
    ResponseEntity<Project> save(Project project);
    
    ResponseEntity deleteByIds(Long[] ids);
    
}
