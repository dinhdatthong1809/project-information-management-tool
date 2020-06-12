package vn.elca.training.tdd.exception.project;

import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.helpers.TranslateHelper;

public class ProjectNumberExistedException extends RuntimeException {
    
    public ProjectNumberExistedException(Integer projectNumber) {
        super(
            TranslateHelper.toLocale(
                I18nMessages.PROJECT_IS_ALREADY_EXISTED_WITH_NUMBER
                            .setParams(projectNumber)
            )
        );
    }
    
}
