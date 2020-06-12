package vn.elca.training.tdd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.dto.ProjectDeleteDto;
import vn.elca.training.tdd.dto.ProjectTableRowDto;
import vn.elca.training.tdd.service.IProjectService;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
    
    @Autowired
    private IProjectService projectService;
    
    @GetMapping(value = "", params = {"keyword", "status", "chunk", "page", "order"})
    public ResponseEntity<Page<ProjectTableRowDto>> findAChunkInPageWithKeywordAndStatus(String keyword, String status, int chunk, int page, Sort.Order order) {
        return projectService.findSortedChunkInPageWithKeywordAndStatus(keyword, status, chunk, page, order);
    }
    
    @GetMapping("statuses")
    public ResponseEntity<List<ProjectStatus>> findAllProjectStatuses() {
        return projectService.findAllStatuses();
    }
    
    @GetMapping("{projectNumber}")
    public ResponseEntity<Project> findByProjectNumber(@PathVariable Integer projectNumber) {
        return projectService.findByProjectNumber(projectNumber);
    }
    
    @PostMapping("")
    public ResponseEntity<Project> save(@RequestBody Project project) {
        return projectService.save(project);
    }
    
    @PutMapping("")
    public ResponseEntity<Project> update(@RequestBody Project project) {
        return projectService.save(project);
    }
    
    @DeleteMapping("")
    public ResponseEntity deleteByIds(@RequestBody ProjectDeleteDto[] projectDeleteDtos) {
        return projectService.deleteByIds(projectDeleteDtos);
    }
    
}
