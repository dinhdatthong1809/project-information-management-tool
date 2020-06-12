package vn.elca.training.tdd.exception.project;

import vn.elca.training.tdd.config.i18n.I18nMessages;
import vn.elca.training.tdd.helpers.TranslateHelper;

public class ProjectUnsortableFieldException extends RuntimeException {
    
    public ProjectUnsortableFieldException(String field) {
        super(
                TranslateHelper.toLocale(
                    I18nMessages.PROJECT_CAN_NOT_BE_SORTED_WITH_FIELD
                                .setParams(field)
                )
        );
    }
    
}
