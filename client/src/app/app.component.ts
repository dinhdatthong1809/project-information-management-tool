import {Component} from "@angular/core";
import {TranslateService} from "@ngx-translate/core";
import {APP_LANGUAGES, DEFAULT_LANGUAGE} from "./i18n/i18n-labels";
import {SessionKeys} from "./constants/session-keys";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.css"]
})
export class AppComponent {

    // @formatter:off

    constructor(private _translateService: TranslateService) {
        this._translateService.addLangs(APP_LANGUAGES);
        this._translateService.setDefaultLang(DEFAULT_LANGUAGE);
        this._translateService.use(
            sessionStorage.getItem(SessionKeys.I18N) ? sessionStorage.getItem(SessionKeys.I18N)
                                                        : DEFAULT_LANGUAGE
        );
    }

    // @formatter:on

}
