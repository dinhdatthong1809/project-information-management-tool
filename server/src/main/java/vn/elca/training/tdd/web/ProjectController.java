package vn.elca.training.tdd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.dto.ProjectTableRowDTO;
import vn.elca.training.tdd.service.IProjectService;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
    
    @Autowired
    private IProjectService projectService;
    
    @GetMapping(value = "", params = {"keyword", "status", "chunk", "page"})
    public ResponseEntity<Page<ProjectTableRowDTO>> findAChunkInPageWithKeywordAndStatus(String keyword, String status, int chunk, int page) {
        return projectService.findAChunkInPageWithKeywordAndStatus(keyword, status, chunk, page);
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
    public ResponseEntity deleteByIds(@RequestBody Long[] ids) {
        return projectService.deleteByIds(ids);
    }
    
}
