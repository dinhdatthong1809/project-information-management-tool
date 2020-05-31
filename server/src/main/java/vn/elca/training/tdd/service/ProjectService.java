package vn.elca.training.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.ProjectEmployee;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.dto.ProjectTableRowDTO;
import vn.elca.training.tdd.exception.ProjectNumberAlreadyExistsException;
import vn.elca.training.tdd.repository.IProjectEmployeeRepository;
import vn.elca.training.tdd.repository.IProjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class ProjectService implements IProjectService {
    
    @Autowired
    private IProjectRepository projectRepository;
    
    @Autowired
    private IProjectEmployeeRepository projectEmployeeRepository;
    
    @Override
    public ResponseEntity<Page<ProjectTableRowDTO>> findAChunkInPageWithKeywordAndStatus(String keyword, String status, int chunk, int page) {
        // @formatter:off
        Page<ProjectTableRowDTO> projectTableRowDTOPage = projectRepository
                                                          .findAChunkInPageWithKeywordAndStatus(
                                                              keyword,
                                                              status,
                                                              PageRequest.of(page - 1, chunk)
                                                          )
                                                          .map(ProjectTableRowDTO::new);
        
        // @formatter:on
        
        return new ResponseEntity<>(projectTableRowDTOPage, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<List<ProjectStatus>> findAllStatuses() {
        List<ProjectStatus> projectStatuses = ProjectStatus.getList();
        
        return new ResponseEntity<>(projectStatuses, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Project> findById(Long id) {
        // @formatter:off
        Project project = projectRepository.findById(id)
                                           .orElseThrow(() -> new EntityNotFoundException("Project not found with id " + id));
        // @formatter:on
        
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Project> findByProjectNumber(Integer projectNumber) {
        // @formatter:off
        Project project = projectRepository.findByProjectNumber(projectNumber)
                                           .orElseThrow(() -> new EntityNotFoundException("Project not found with number " + projectNumber));
        // @formatter:on
    
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Project> save(Project project) {
    
        Project projectInResponse;
        
        // if in update state
        if (project.getId() != null) {
            projectInResponse = projectRepository.save(project);
            return new ResponseEntity<>(projectInResponse, HttpStatus.OK);
        }
        
        if (isExistedByProjectNumber(project.getProjectNumber())) {
            throw new ProjectNumberAlreadyExistsException(project.getProjectNumber());
        }
    
        projectInResponse = projectRepository.save(project);

        List<ProjectEmployee> projectEmployees = project.getProjectEmployees();

        for (ProjectEmployee projectEmployee : projectEmployees) {
            projectEmployee.getProjectEmployeeId().setProjectId(project.getId());
        }

        projectEmployeeRepository.saveAll(projectEmployees);
        
        return new ResponseEntity<>(projectInResponse, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity deleteByIds(Long[] ids) {
        projectRepository.deleteByIds(ids);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    private boolean isExistedByProjectNumber(Integer projectNumber) {
        return projectRepository.isExistedByProjectNumber(projectNumber);
    }
    
}
