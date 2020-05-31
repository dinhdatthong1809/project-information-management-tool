import {Component, OnInit} from "@angular/core";
import {TranslateService} from "@ngx-translate/core";
import {APP_LANGUAGES, I18nLabels} from "../../i18n/i18n-labels";
import {MenuItem} from "../../abstract/menu";
import {SessionKeys} from "../../constants/session-keys";

@Component({
    selector: "app-nav-bar",
    templateUrl: "./nav-bar.component.html",
    styleUrls: ["./nav-bar.component.css"]
})
export class NavBarComponent implements OnInit {

    i18nLabels = I18nLabels;

    items: MenuItem[] = [
        {
            name: I18nLabels.HELP,
            url: "/",
        },
        {
            name: I18nLabels.LOG_OUT,
            url: "/"
        }
    ];

    applanguages: string[] = APP_LANGUAGES;

    constructor(private _translateService: TranslateService) {

    }

    ngOnInit() {

    }

    changeLanguage(language: string): void {
        sessionStorage.setItem(SessionKeys.I18N, language);
        this._translateService.use(language);
    }

    getCurrentLanguage(): string {
        return this._translateService.currentLang;
    }

}
