import {Directive, ElementRef, HostListener} from "@angular/core";
import {AppConstants} from "../../../constants/app-constants";

@Directive({
    selector: "[appDateInput]"
})
export class DateInputDirective {

    constructor(private el: ElementRef) {

    }

    @HostListener("keypress", ["$event"]) onKeyDown(event) {
        return AppConstants.IS_VALID_DATE_CHARACTERS(event.charCode);
    }

}
