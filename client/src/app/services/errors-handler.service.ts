import {ErrorHandler, Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {AlertHelper} from "../helpers/alert-helper";
import * as HttpStatus from "http-status-codes";
import {AppUrl} from "../constants/app-url";

@Injectable({
    providedIn: 'root'
})
export class ErrorsHandlerService implements ErrorHandler {

    constructor(private router: Router) {

    }

    handleError(error: any): void {
        if (error === null) {
            return;
        }

        if (error instanceof ErrorEvent) {
            AlertHelper.error("Error has occurred in client");
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

            AlertHelper.error(error.error.message);
            return;
        }

        window.location.href = `/${AppUrl.ERROR}`;
    }

}
