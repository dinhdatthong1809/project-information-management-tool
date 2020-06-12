package vn.elca.training.tdd.exception.project;

import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.helpers.TranslateHelper;

public class ProjectConcurrentDeleteException extends RuntimeException {
    
    public ProjectConcurrentDeleteException(Integer projectNumber) {
        this(projectNumber, null);
    }
    
    public ProjectConcurrentDeleteException(Integer projectNumber, Throwable ex) {
        super(
                TranslateHelper.toLocale(
                    I18nMessages.PROJECT_WITH_NUMBER_WAS_DELETED_BY_ANOTHER_USER_PLEASE_GO_BACK_TO_HOME_PAGE
                                .setParams(projectNumber)
                ),
                ex
        );
    }
    
}
