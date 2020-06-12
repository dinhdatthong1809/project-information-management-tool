import {NgbDateAdapter, NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class CustomNgbDateAdapterService extends NgbDateAdapter<string> {

    readonly DELIMITER = "-";

    fromModel(value: string | null): NgbDateStruct | null {
        if (value) {
            let date = value.split(this.DELIMITER);
            return {
                day: parseInt(date[0]),
                month: parseInt(date[1]),
                year: parseInt(date[2])
            };
        }

        return null;
    }

    toModel(date: NgbDateStruct | null): string | null {
        if (!date) {
            return null;
        }

        let day: string = date.day < 10 ? "0" + date.day.toString() : date.day.toString();
        let month: string = date.month < 10 ? "0" + date.month.toString() : date.month.toString();

        return day + this.DELIMITER + month + this.DELIMITER + date.year;
    }

}
