import {Injectable} from '@angular/core';
import {ServicesModule} from "./services.module";
import {NgbDatepickerI18n, NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {I18N_DATE_DICTIONARY} from "../../i18n/i18n-labels";
import {TranslateService} from "@ngx-translate/core";

@Injectable({
    providedIn: ServicesModule
})
export class CustomDatePickerI18nService extends NgbDatepickerI18n {

    constructor(private _translateService: TranslateService) {
        super();
    }

    getWeekdayShortName(weekday: number): string {
        return I18N_DATE_DICTIONARY[this._translateService.currentLang].weekdays[weekday - 1];
    }
    getMonthShortName(month: number): string {
        return I18N_DATE_DICTIONARY[this._translateService.currentLang].months[month - 1];
    }
    getMonthFullName(month: number): string {
        return this.getMonthShortName(month);
    }

    getDayAriaLabel(date: NgbDateStruct): string {
        return `${date.day}-${date.month}-${date.year}`;
    }

}
