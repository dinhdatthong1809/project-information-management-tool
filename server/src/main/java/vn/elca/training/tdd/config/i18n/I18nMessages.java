package vn.elca.training.tdd.config.i18n;

public enum I18nMessages {
    
    API_NOT_FOUND("apiNotFound"),
    
    END_DATE_MUST_BE_AFTER_START_DATE("endDateMustBeAfterStartDate"),
    
    MALFORMED_JSON_REQUEST("malformedJsonRequest"),
    
    SOMETHING_BAD_HAPPENED("somethingBadHappened"),
    
    THIS_PROJECT_WAS_UPDATED_BY_AN_OTHER_USER_PLEASE_REFRESH_PAGE("thisProjectWasUpdatedByAnotherUserPleaseRefreshPage");
    
    private final String keyLanguage;
    
    I18nMessages(String keyLanguage) {
        this.keyLanguage = keyLanguage;
    }
    
    public String getKeyLanguage() {
        return this.keyLanguage;
    }
    
}
