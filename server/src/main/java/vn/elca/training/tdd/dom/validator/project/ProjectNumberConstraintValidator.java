/*
 * TaskDeadlineConstraintValidator
 * 
 * Project: Training
 * 
 * Copyright 2015 by ELCA
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of ELCA. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license
 * agreement you entered into with ELCA.
 */

package vn.elca.training.tdd.dom.validator.project;

import org.springframework.beans.factory.annotation.Autowired;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.repository.IProjectRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * NOT USED!!!
 * should find a way to inject repository into custom validator
 * tried @Component on this class and failed
 *
 * */
public class ProjectNumberConstraintValidator implements ConstraintValidator<ProjectNumberValid, Project> {
    
    @Autowired
    private IProjectRepository projectRepository;
    
    @Override
    public void initialize(ProjectNumberValid constraintAnnotation) {
    
    }
    
    @Override
    public boolean isValid(Project project, ConstraintValidatorContext context) {
        Project projectQuery = projectRepository.findByProjectNumber(project.getProjectNumber()).orElse(null);
    
        if (projectQuery == null) {
            return true;
        }
        
        customMessage(context, "Project number " + project.getProjectNumber() + " is already existed");
        return false;
    }
    
    private void customMessage(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
    
}
