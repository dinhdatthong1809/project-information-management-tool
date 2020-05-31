import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProjectStatusDTO} from "../../../services/dto/project-status-dto";
import {ProjectService} from "../../../services/services/project.service";
import {ProjectTableRowDTO} from "../../../services/dto/project-table-row-dto";
import {AlertHelper} from "../../../../helpers/alert-helper";
import {Project} from "../../../../dom/project";
import {SweetAlertResult} from "sweetalert2";
import {AppUrl} from "../../../../constants/app-url";
import {I18nLabels} from "../../../../i18n/i18n-labels";
import {SessionKeys} from "../../../../constants/session-keys";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";

@Component({
    selector: "app-projects-list",
    templateUrl: "./projects-list.component.html",
    styleUrls: ["./projects-list.component.css"]
})
export class ProjectsListComponent implements OnInit {

    // @formatter:off

    i18nLabels = I18nLabels;

    filterProjectForm: FormGroup;
    submitted: boolean = false;

    projects: ProjectTableRowDTO[] = [];

    statuses: ProjectStatusDTO[] = [];
    chunk: number = 10;
    page: number = 1;
    totalProject: number = 0;

    selectedIds: number[] = [];

    constructor(
        private _formBuilder: FormBuilder,
        private _projectService: ProjectService,
        private _translateService: TranslateService
    ) {
        this._translateService.onLangChange.subscribe((event: LangChangeEvent) => {
            this.getAllProjectStatuses();
        });
    }

    ngOnInit(): void {
        this.initForm();

        this.filterProjectsInChunkAndPage();
        this.getAllProjectStatuses();
    }

    getDetailLink(projectNumber: number): string {
        return `/${AppUrl.PROJECT}/${projectNumber}`;
    }

    get getForm() {
        return this.filterProjectForm.controls;
    }

    resetForm() {
        this.submitted = false;
        this.page = 1;

        for (let key in this.getForm) {
            this.getForm[key].setValue("");
        }

        sessionStorage.setItem(SessionKeys.KEYWORD, "");
        sessionStorage.setItem(SessionKeys.STATUS, "");

        this.filterProjectsInChunkAndPage();
    }

    private initForm(): void {
        this.filterProjectForm = this._formBuilder.group({
            keyword: [sessionStorage.getItem(SessionKeys.KEYWORD) ? sessionStorage.getItem(SessionKeys.KEYWORD) : ""],
            status: [sessionStorage.getItem(SessionKeys.STATUS) ? sessionStorage.getItem(SessionKeys.STATUS) : ""],
        });
    }

    onSubmitFilterProjectForm(): void {
        this.submitted = true;

        sessionStorage.setItem(SessionKeys.KEYWORD, this.getForm.keyword.value);
        sessionStorage.setItem(SessionKeys.STATUS, this.getForm.status.value);

        this.filterProjectsInChunkAndPage();
    }

    getAllProjectStatuses(): void {
        this._projectService
            .findAllProjectStatuses()
            .subscribe((data: ProjectStatusDTO[]) => {
                this.statuses = data;
            });
    }

    filterProjectsInChunkAndPage(): void {
        let keyword: string = this.getDataFromSession("keyword");
        let status: string = this.getDataFromSession("status");

        this._projectService
            .filterWithKeywordAndStatusInChunkAndPage(keyword, status, this.chunk, this.page)
            .subscribe((data: any) => {
                this.projects = data.content;
                this.totalProject = data.totalElements;
            });
    }

    private getDataFromSession(key: string): string {
        if (sessionStorage.getItem(key)) {
            return sessionStorage.getItem(key);
        }

        return this.getForm[key].value;
    }

    goToPage(page: number) {
        this.page = page;
        this.filterProjectsInChunkAndPage();
    }

    askBeforeDeleteOne(project: Project) {
        AlertHelper.ask(`You will delete project <strong>${project.name}</strong>`)
                   .then((result: SweetAlertResult) => {
                       if (result.value) {
                           this.deleteProjectByIds([project.id]);
                       }
                   });
    }

    askBeforeDeleteMulti() {
        AlertHelper.ask(`You will delete selected projects`)
                   .then((result: SweetAlertResult) => {
                       if (result.value) {
                           this.deleteProjectByIds(this.selectedIds);
                       }
                   });
    }

    private deleteProjectByIds(ids: number[]): void {
        this._projectService
            .deleteByIds(ids)
            .subscribe((data: any) => {
                AlertHelper.success("Deleted successfully");
                this.selectedIds = [];
                this.filterProjectsInChunkAndPage();
            });
    }

    selectProject(event: any, id: number): void {
        this.selectedIds = this.selectedIds.filter(item => item !== id);

        if (event.target.checked) {
            this.selectedIds.push(id);
        }
    }

    // @formatter:on

}
