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

import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.helpers.TranslateHelper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ProjectEndDateConstraintValidator implements ConstraintValidator<ProjectEndDateValid, Project> {
    
    @Override
    public void initialize(ProjectEndDateValid constraintAnnotation) {
    
    }
    
    @Override
    public boolean isValid(Project project, ConstraintValidatorContext context) {
        LocalDate endDate = project.getEndDate();
        
        if (endDate == null) {
            return true;
        }
        
        LocalDate startDate = project.getStartDate();
        
        if (!(endDate.compareTo(startDate) > 0)) {
            customMessage(context, TranslateHelper.toLocale(I18nMessages.END_DATE_MUST_BE_AFTER_START_DATE));
            return false;
        }
        
        return true;
    }
    
    private void customMessage(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
    
}
