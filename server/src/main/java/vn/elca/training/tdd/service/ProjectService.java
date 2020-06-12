package vn.elca.training.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.dom.ProjectEmployee;
import vn.elca.training.tdd.dom.enums.ProjectStatus;
import vn.elca.training.tdd.dto.ProjectDeleteDto;
import vn.elca.training.tdd.dto.ProjectTableRowDto;
import vn.elca.training.tdd.exception.project.*;
import vn.elca.training.tdd.repository.IProjectEmployeeRepository;
import vn.elca.training.tdd.repository.IProjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class ProjectService implements IProjectService {
    
    @Autowired
    private IProjectRepository projectRepository;
    
    @Autowired
    private IProjectEmployeeRepository projectEmployeeRepository;
    
    private boolean isExistedByProjectNumber(Integer projectNumber) {
        return projectRepository.isExistedByProjectNumber(projectNumber);
    }
    
    private Project saveProject(Project project) {
        try {
            return projectRepository.save(project);
        }
        catch (ObjectOptimisticLockingFailureException ex) {
            throw new ProjectConcurrentUpdateException(project.getProjectNumber(), ex);
        }
    }
    
    @Override
    public ResponseEntity<Page<ProjectTableRowDto>> findSortedChunkInPageWithKeywordAndStatus(String keyword, String status, int chunk, int page, Sort.Order order) {
        String field = order.getProperty();
        
        if (!(new Project().isSortable(field))) {
            throw new ProjectUnsortableFieldException(field);
        }
        
        // @formatter:off
        Page<ProjectTableRowDto> projectTableRowDTOPage = projectRepository
                                                          .findSortedChunkInPageWithKeywordAndStatus(
                                                              keyword,
                                                              status,
                                                              PageRequest.of(page - 1, chunk),
                                                              order
                                                          )
                                                          .map(ProjectTableRowDto::new);
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
        
        // TODO: bug in update project feature: can not remove employee from project
        // if in update state
        if (project.getId() != null) {
            if (!isExistedByProjectNumber(project.getProjectNumber())) {
                throw new ProjectConcurrentDeleteException(project.getProjectNumber());
            }
            
            projectInResponse = saveProject(project);
        } else {
            if (isExistedByProjectNumber(project.getProjectNumber())) {
                throw new ProjectNumberExistedException(project.getProjectNumber());
            }
            
            projectInResponse = saveProject(project);
    
            List<ProjectEmployee> projectEmployees = project.getProjectEmployees();
    
            for (ProjectEmployee projectEmployee : projectEmployees) {
                projectEmployee.getProjectEmployeeId().setProjectId(project.getId());
            }
    
            projectEmployeeRepository.saveAll(projectEmployees);
        }
        
        return new ResponseEntity<>(projectInResponse, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity deleteByIds(ProjectDeleteDto[] projectDeleteDtos) {
        // @formatter:off
        List<Project> projects = Arrays.stream(projectDeleteDtos)
                                       .map(projectDeleteDto -> {
                                           if (projectDeleteDto.getStatus() != ProjectStatus.NEW) {
                                               throw new ProjectInvalidDeleteException(projectDeleteDto.getProjectNumber());
                                           }
                                           
                                           return projectDeleteDto.getProject();
                                       })
                                       .collect(Collectors.toList());
        // @formatter:on
        
        projectRepository.deleteByIds(projects);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
