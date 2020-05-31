import {NgbDateParserFormatter, NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {Injectable} from "@angular/core";

@Injectable({
    providedIn: "root"
})
export class CustomNgbDateParserFormatterService extends NgbDateParserFormatter {

    readonly DELIMITER = "-";

    parse(value: string): NgbDateStruct | null {
        if (value) {
            let date = value.split(this.DELIMITER);
            return {
                day : parseInt(date[0]),
                month : parseInt(date[1]),
                year : parseInt(date[2])
            };
        }
        return null;
    }

    format(date: NgbDateStruct | null): string {
        if (!date) {
            return "";
        }

        let day: string = date.day < 10 ? "0" + date.day.toString() : date.day.toString();
        let month: string = date.month < 10 ? "0" + date.month.toString() : date.month.toString();

        return day + this.DELIMITER + month + this.DELIMITER + date.year;
    }
}
