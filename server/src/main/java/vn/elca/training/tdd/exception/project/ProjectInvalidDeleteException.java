package vn.elca.training.tdd.exception.project;

import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.helpers.TranslateHelper;

public class ProjectInvalidDeleteException extends RuntimeException {
    
    public ProjectInvalidDeleteException(Integer projectNumber) {
        super(
            TranslateHelper.toLocale(
                I18nMessages.PROJECT_WITH_NUMBER_WAS_UPDATED_BY_ANOTHER_USER_PLEASE_REFRESH_PAGE
                            .setParams(projectNumber)
            )
        );
    }
    
}
