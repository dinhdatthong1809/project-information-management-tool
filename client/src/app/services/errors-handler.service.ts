import {ErrorHandler, Injectable} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import * as HttpStatus from "http-status-codes";
import {AppUrl} from "../constants/app-url";
import {AlertService} from "../modules/services/alert.service";

@Injectable({
    providedIn: 'root'
})
export class ErrorsHandlerService implements ErrorHandler {

    constructor(private _alertService: AlertService) {

    }

    handleError(error: any): void {
        if (!error) {
            return;
        }

        if (error instanceof ErrorEvent) {
            this._alertService.error("Error has occurred in client");
            return;
        }

        if (error instanceof HttpErrorResponse) {

            // lost connection to server app
            if (error.status === 0) {
                window.location.href = `/${AppUrl.ERROR}`;
                return;
            }

            if (error.status === HttpStatus.NOT_FOUND) {
                window.location.href = `/${AppUrl.ERROR}`;
                return;
            }

            this._alertService.error(error.error.message);
            return;
        }

        window.location.href = `/${AppUrl.ERROR}`;
    }

}
