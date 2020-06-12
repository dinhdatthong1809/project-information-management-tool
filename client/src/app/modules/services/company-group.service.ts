import {CompanyGroupDto} from "./dto/company-group-dto";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {AbstractService} from "../../services/abstract-service";
import {Injectable} from "@angular/core";
import {ServicesModule} from "./services.module";
import {ApiUrl} from "../../constants/api-url";

@Injectable({
    providedIn: ServicesModule
})
export class CompanyGroupService extends AbstractService {

    findAll(): Observable<CompanyGroupDto[]> {
        return super.get<CompanyGroupDto[]>(ApiUrl.COMPANY_GROUP)
                    .pipe(catchError(super.handleError));
    }

}
