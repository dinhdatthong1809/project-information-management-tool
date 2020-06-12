enum Language {

    en = "English", // the first is default

    fr = "French",

}

export const APP_LANGUAGES: string[] = Object.keys(Language);

export const DEFAULT_LANGUAGE = Object.keys(Language)[0];

export const I18N_DATE_DICTIONARY = {

    en: {
        weekdays: ['Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'],
        months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },

    fr: {
        weekdays: ['Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa', 'Di'],
        months: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin', 'Juil', 'Aou', 'Sep', 'Oct', 'Nov', 'Déc']
    }

};

export class I18nLabels {

    static readonly APP_TITLE: string = "appTitle";

    static readonly ARE_YOU_SURE: string = "areYouSure";

    static readonly CANCEL: string = "cancel";

    static readonly CHOOSE_GROUP: string = "chooseGroup";

    static readonly CREATE_PROJECT: string = "createProject";

    static readonly CUSTOMER: string = "customer";

    static readonly CUSTOMER_HAS_LESS_THAN_50_CHARACTERS: string = "customerHasLessThan50Characters";

    static readonly DELETE: string = "delete";

    static readonly DELETED_SUCCESSFULLY: string = "deletedSuccessfully";

    static readonly EDIT_PROJECT_INFORMATION: string = "editProjectInformation";

    static readonly END_DATE: string = "endDate";

    static readonly GROUP: string = "group";

    static readonly HELP: string = "help";

    static readonly KEYWORD_PLACE_HOLDER: string = "keywordPlaceHolder";

    static readonly LOG_OUT: string = "logOut";

    static readonly MEMBERS: string = "members";

    static readonly NAME: string = "name";

    static readonly NEW: string = "new";

    static readonly NEW_PROJECT: string = "newProject";

    static readonly NUMBER: string = "number";

    static readonly PLEASE_ENTER_ALL_THE_MANDATORY_FIELDS: string = "pleaseEnterAllTheMandatoryFields";

    static readonly PROJECT: string = "project";

    static readonly PROJECT_NAME: string = "projectName";

    static readonly PROJECT_NAME_HAS_BEEN_SAVED: string = "projectNameHasBeenSaved";

    static readonly PROJECT_NAME_HAS_LESS_THAN_50_CHARACTERS: string = "projectNameHasLessThan50Characters";

    static readonly PROJECT_NUMBER: string = "projectNumber";

    static readonly PROJECT_NUMBER_IS_OVER_MAXIMUM: string = "projectNumberIsOverMaximum";

    static readonly PROJECT_NUMBER_IS_POSITIVE: string = "projectNumberIsPositive";

    static readonly PROJECT_STATUS_PLACE_HOLDER: string = "projectStatusPlaceHolder";

    static readonly PROJECTS_LIST: string = "projectsList";

    static readonly RESET_SEARCH: string = "resetSearch";

    static readonly SEARCH_PROJECT: string = "searchProject";

    static readonly START_DATE: string = "startDate";

    static readonly STATUS: string = "status";

    static readonly SUPPLIER: string = "supplier";

    static readonly THE_PROJECT_NUMBER_ALREADY_EXISTED_PLEASE_SELECT_A_DIFFERENT_PROJECT_NUMBER: string = "theProjectNumberAlreadyExistedPleaseSelectADifferentProjectNumber";

    static readonly UPDATE_PROJECT: string = "updateProject";

    static readonly YES: string = "yes";

    static readonly YOU_WILL_DELETE_PROJECT_NAME: string = "youWillDeleteProjectName";

    static readonly YOU_WILL_DELETE_SELECTED_PROJECTS: string = "youWillDeleteSelectedProjects";

}
