import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CompanyGroupService} from "../../../services/services/company-group.service";
import {CompanyGroupDTO} from "../../../services/dto/company-group-dto";
import {ProjectStatusDTO} from "../../../services/dto/project-status-dto";
import {ProjectService} from "../../../services/services/project.service";
import {EmployeeService} from "../../../services/services/employee.service";
import {EmployeeDTO} from "../../../services/dto/employee-dto";
import {Project} from "../../../../dom/project";
import {AlertHelper} from "../../../../helpers/alert-helper";
import {AppConstants} from "../../../../constants/app-constants";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import * as HttpStatus from 'http-status-codes';
import {Router} from "@angular/router";
import {AppUrl} from "../../../../constants/app-url";
import {HttpErrorResponse} from "@angular/common/http";
import {I18nLabels} from "../../../../i18n/i18n-labels";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";

@Component({
    selector: "app-new-project",
    templateUrl: "./new-project.component.html",
    styleUrls: ["./new-project.component.css"],
})
export class NewProjectComponent implements OnInit {

    i18nLabels = I18nLabels;

    newProjectForm: FormGroup;
    submitted: boolean = false;

    isExistedProjectNumber: boolean = false;
    maxProjectNumber: number = AppConstants.MAX_PROJECT_NUMBER;

    groups: CompanyGroupDTO[] = [];
    employees: EmployeeDTO[] = [];
    statuses: ProjectStatusDTO[] = [];

    constructor(
        private _formBuilder: FormBuilder,
        private _router: Router,
        private _companyGroupService: CompanyGroupService,
        private _projectService: ProjectService,
        private _employeeService: EmployeeService,
        private _translateService: TranslateService
    ) {
        this._translateService.onLangChange.subscribe((event: LangChangeEvent) => {
            this.getAllProjectStatuses();
        });
    }

    ngOnInit(): void {
        this.initForm();

        this.getAllEmployees();
        this.getAllProjectStatuses();
        this.getAllGroups();
    }

    get getForm() {
        return this.newProjectForm.controls;
    }

    resetForm() {
        this.submitted = false;
        this.isExistedProjectNumber = false;

        this._router.navigate([AppUrl.PROJECT_LIST]);
    }

    private initForm(): void {
        this.newProjectForm = this._formBuilder.group(
            {
                projectNumber: ["", [
                    Validators.required,
                    Validators.pattern(AppConstants.REGEX_NUMBER_ONLY),
                    Validators.min(1),
                    Validators.max(AppConstants.MAX_PROJECT_NUMBER),
                ]],
                name: ["", [
                    Validators.required,
                    Validators.maxLength(50)
                ]],
                customer: ["", [
                    Validators.required,
                    Validators.maxLength(50)
                ]],
                companyGroupId: ["", [Validators.required]],
                projectEmployees: [],
                status: ["", [Validators.required]],
                startDate: [, [Validators.required]],
                endDate: []
            }
        );
    }

    onSubmitNewProjectForm(): void {
        this.submitted = true;
        this.isExistedProjectNumber = false;

        if (this.newProjectForm.invalid) {
            return;
        }

        let project: Project = new Project(this.newProjectForm.value);

        this._projectService
            .save(project)
            .pipe(catchError(this.handleError.bind(this)))
            .subscribe((data: any) => {
                this._translateService
                    .get(this.i18nLabels.PROJECT_NAME_HAS_BEEN_SAVED, {name: project.name})
                    .subscribe((text: string) => {
                        AlertHelper.success(text);
                        this._router.navigate([AppUrl.PROJECT_LIST]);
                    });
            });
    }

    getAllGroups(): void {
        this._companyGroupService
            .findAll()
            .subscribe((data: CompanyGroupDTO[]) => {
                this.groups = data;
            });
    }

    getAllProjectStatuses(): void {
        this._projectService
            .findAllProjectStatuses()
            .subscribe((data: ProjectStatusDTO[]) => {
                this.statuses = data;
                this.getForm.status.setValue(this.statuses[0].id);
            });
    }

    // TODO: MUST not fetch all employees
    getAllEmployees(): void {
        this._employeeService
            .findAll()
            .subscribe((data: EmployeeDTO[]) => {
                this.employees = data;
            });
    }

    handleError(error: HttpErrorResponse): Observable<never> {
        if (error.status === HttpStatus.UNPROCESSABLE_ENTITY) {
            this.isExistedProjectNumber = true;
            return throwError(null);
        }

        return throwError(error);
    }

}
