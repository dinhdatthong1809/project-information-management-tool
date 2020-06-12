import {AbstractService} from "../../services/abstract-service";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {EmployeeDto} from "./dto/employee-dto";
import {Injectable} from "@angular/core";
import {ServicesModule} from "./services.module";
import {ApiUrl} from "../../constants/api-url";

@Injectable({
    providedIn: ServicesModule
})
export class EmployeeService extends AbstractService {

    findAll(): Observable<EmployeeDto[]> {
        return super.get<EmployeeDto[]>(ApiUrl.EMPLOYEE)
                    .pipe(catchError(super.handleError));
    }

}
