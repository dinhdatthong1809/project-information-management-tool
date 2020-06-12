import {Injectable} from '@angular/core';
import Swal, {SweetAlertResult} from "sweetalert2";
import {TranslateService} from "@ngx-translate/core";
import {I18nLabels} from "../../i18n/i18n-labels";

@Injectable({
    providedIn: 'root'
})
export class AlertService {

    i18nLabels = I18nLabels;

    constructor(private _translateService: TranslateService) {

    }

    success(message: string): Promise<SweetAlertResult> {
        return Swal.fire({
            icon: "success",
            html: message,
        })
    }

    error(message: string): Promise<SweetAlertResult> {
        return Swal.fire({
            icon: "error",
            html: message,
        })
    }

    warn(message: string): Promise<SweetAlertResult> {
        return Swal.fire({
            icon: "warning",
            html: message,
        })
    }

    ask(message: string): Promise<SweetAlertResult> {
        let title: string = "";
        let yesMessage: string = "";
        let cancelMessage: string = "";

        this._translateService
            .get(this.i18nLabels.ARE_YOU_SURE)
            .subscribe((text: string) => {
                title = text;
            });

        this._translateService
            .get(this.i18nLabels.YES)
            .subscribe((text: string) => {
                yesMessage = text;
            });

        this._translateService
            .get(this.i18nLabels.CANCEL)
            .subscribe((text: string) => {
                cancelMessage = text;
            });

        return Swal.fire({
            icon: "warning",
            title: `${title}?`,
            html: message,
            confirmButtonColor: '#3085d6',
            confirmButtonText: yesMessage,
            showCancelButton: true,
            cancelButtonColor: "#d33",
            cancelButtonText: cancelMessage,
            focusCancel: true
        });
    }

    loading(): Promise<SweetAlertResult> {
        return Swal.fire({
            title: "",
            allowOutsideClick: false,
            onBeforeOpen: () => {
                Swal.showLoading();
            },
        });
    }

    close(): void {
        Swal.close();
    }

}
