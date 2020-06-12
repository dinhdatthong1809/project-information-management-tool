package vn.elca.training.tdd.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.dto.ProjectDeleteDto;
import vn.elca.training.tdd.dto.ProjectTableRowDto;

import javax.validation.Valid;
import java.util.List;

public interface IProjectService {
    
    ResponseEntity<Page<ProjectTableRowDto>> findSortedChunkInPageWithKeywordAndStatus(String keyword, String status, int chunk, int page, Sort.Order order);
    
    ResponseEntity<List<ProjectStatus>> findAllStatuses();
    
    ResponseEntity<Project> findById(Long id);
    
    ResponseEntity<Project> findByProjectNumber(Integer projectNumber);
    
    ResponseEntity<Project> save(@Valid Project project);
    
    ResponseEntity deleteByIds(ProjectDeleteDto[] projectDeleteDtos);
    
}
