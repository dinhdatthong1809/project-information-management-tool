package vn.elca.training.tdd.config.i18n;

import java.util.Arrays;

public enum I18nMessages {
    
    API_NOT_FOUND("apiNotFound"),
    
    CONCURRENT_UPDATE_OCCURRED("concurrentUpdateOccurred"),
    
    END_DATE_MUST_BE_AFTER_START_DATE("endDateMustBeAfterStartDate"),
    
    MALFORMED_JSON_REQUEST("malformedJsonRequest"),
    
    PROJECT_CAN_NOT_BE_SORTED_WITH_FIELD("projectCanNotBeSortedWithField"),
    
    PROJECT_IS_ALREADY_EXISTED_WITH_NUMBER("projectIsAlreadyExistedWithNumber"),
    
    PROJECT_WITH_NUMBER_WAS_DELETED_BY_ANOTHER_USER_PLEASE_GO_BACK_TO_HOME_PAGE("projectWithNumberWasDeletedByAnotherUserPleaseGoBackToHomePage"),
    
    PROJECT_WITH_NUMBER_WAS_UPDATED_BY_ANOTHER_USER_PLEASE_REFRESH_PAGE("projectWithNumberWasUpdatedByAnotherUserPleaseRefreshPage"),
    
    SOMETHING_BAD_HAPPENED("somethingBadHappened");
    private String keyLanguage;
    
    private Object[] params;
    
    I18nMessages(String keyLanguage, Object... params) {
        this.params = params;
        this.keyLanguage = keyLanguage;
    }
    
    public String getKeyLanguage() {
        return this.keyLanguage;
    }
    
    public Object[] getParams() {
        return this.params;
    }
    
    public I18nMessages setParams(Object... params) {
        this.params = params;
        return this;
    }
}
