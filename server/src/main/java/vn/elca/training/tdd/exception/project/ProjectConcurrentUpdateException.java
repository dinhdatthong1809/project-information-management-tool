package vn.elca.training.tdd.exception.project;

import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.helpers.TranslateHelper;

public class ProjectConcurrentUpdateException extends RuntimeException {
    
    public ProjectConcurrentUpdateException(Integer projectNumber) {
        this(projectNumber, null);
    }
    
    public ProjectConcurrentUpdateException(Integer projectNumber, Throwable ex) {
        super(
            TranslateHelper.toLocale(
                I18nMessages.PROJECT_WITH_NUMBER_WAS_UPDATED_BY_ANOTHER_USER_PLEASE_REFRESH_PAGE
                            .setParams(projectNumber)
            ),
            ex
        );
    }
    
}
