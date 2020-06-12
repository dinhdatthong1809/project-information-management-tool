export class AppConstants {

    static readonly MAX_PROJECT_NUMBER: number = 2147483647;

    static readonly REGEX_NUMBER_ONLY: RegExp = /^(0|[1-9]\d*)?$/;

    static IS_VALID_DATE_CHARACTERS(keyCode: number): boolean {
        if (keyCode >= "0".charCodeAt(0) && keyCode <= "9".charCodeAt(0)) {
            return true;
        }

        if (keyCode === "-".charCodeAt(0)
                || keyCode === "/".charCodeAt(0)
                || keyCode === ".".charCodeAt(0)) {
            return true;
        }

        return false;
    }

}
