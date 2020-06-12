import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Injectable} from "@angular/core";
import {DEFAULT_LANGUAGE} from "../i18n/i18n-labels";
import {SessionKeys} from "../constants/session-keys";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {AlertService} from "../modules/services/alert.service";

@Injectable({
    providedIn: 'root'
})
export class AbstractService {

    //@formatter:off

    commonOptions = {
        headers: new HttpHeaders({
            "Accept-Language": sessionStorage.getItem(SessionKeys.I18N) ? sessionStorage.getItem(SessionKeys.I18N)
                                                                        : DEFAULT_LANGUAGE
        })
    };

    constructor(
        private _http: HttpClient,
        private _translateService: TranslateService,
        private _alertService: AlertService,
    ) {
        this._translateService.onLangChange.subscribe((event: LangChangeEvent) => {
            this.commonOptions = {
                headers: new HttpHeaders({
                    "Accept-Language": sessionStorage.getItem(SessionKeys.I18N) ? sessionStorage.getItem(SessionKeys.I18N)
                                                                                : DEFAULT_LANGUAGE
                })
            };
        });
    }

    handleError(error: HttpErrorResponse): Observable<never>  {
        return throwError(error);
    }

    get <RESPONSE_TYPE> (url: string): Observable<RESPONSE_TYPE> {
        return this._http.get<RESPONSE_TYPE>(url, this.commonOptions);
    }

    post <REQUEST_TYPE, RESPONSE_TYPE> (url: string, body: REQUEST_TYPE): Observable<RESPONSE_TYPE>  {
        this._alertService.loading();
        return this._http.post<RESPONSE_TYPE>(url, body, this.commonOptions);
    }

    put <REQUEST_TYPE, RESPONSE_TYPE> (url: string, body: REQUEST_TYPE): Observable<RESPONSE_TYPE>  {
        this._alertService.loading();
        return this._http.put<RESPONSE_TYPE>(url, body, this.commonOptions);
    }

    delete <REQUEST_TYPE, RESPONSE_TYPE> (url: string, body: REQUEST_TYPE): Observable<RESPONSE_TYPE>  {
        this._alertService.loading();
        let options = {
            headers: new HttpHeaders({
                "Content-Type": "application/json"
            }),
            body: body
        };

        return this._http.delete<RESPONSE_TYPE>(url, options);
    }

    //@formatter:on

}
