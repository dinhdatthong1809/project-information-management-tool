import {Component, ElementRef, OnInit} from "@angular/core";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProjectStatusDto} from "../../../services/dto/project-status-dto";
import {ProjectService} from "../../../services/project.service";
import {ProjectTableRowDto} from "../../../services/dto/project-table-row-dto";
import {SweetAlertResult} from "sweetalert2";
import {AppUrl} from "../../../../constants/app-url";
import {I18nLabels} from "../../../../i18n/i18n-labels";
import {SessionKeys} from "../../../../constants/session-keys";
import {LangChangeEvent, TranslateService} from "@ngx-translate/core";
import {ProjectDeleteDto} from "../../../services/dto/project-delete-dto";
import {AlertService} from "../../../services/alert.service";
import {ProjectStatus} from "../../../../dom/project";
import {SortOrder, SortOrderType} from "../../../../enums/sort-order";

@Component({
    selector: "app-projects-list",
    templateUrl: "./projects-list.component.html",
    styleUrls: ["./projects-list.component.css"]
})
export class ProjectsListComponent implements OnInit {

    // @formatter:off

    i18nLabels = I18nLabels;

    filterProjectForm: FormGroup;

    projectTableRowDtos: ProjectTableRowDto[] = [];

    statuses: ProjectStatusDto[] = [];
    chunk: number = 5;
    page: number = 1;
    totalProject: number = 0;

    selectedProjectDeleteDtos: ProjectDeleteDto[] = [];

    ProjectStatus = ProjectStatus;

    currentSortField: string = "projectNumber";
    isAscending: boolean = true;
    sortOrder: SortOrder = new SortOrder(this.currentSortField, this.isAscending ? SortOrderType.ASC : SortOrderType.DESC);

    constructor(
        private _el: ElementRef,
        private _formBuilder: FormBuilder,
        private _projectService: ProjectService,
        private _translateService: TranslateService,
        private _alertService: AlertService
    ) {
        this._translateService.onLangChange.subscribe((event: LangChangeEvent) => {
            this.getAllProjectStatuses();
            this.onSubmitFilterProjectForm();
        });
    }

    private initForm(): void {
        this.filterProjectForm = this._formBuilder.group({
            keyword: [sessionStorage.getItem(SessionKeys.KEYWORD) ? sessionStorage.getItem(SessionKeys.KEYWORD) : ""],
            status: [sessionStorage.getItem(SessionKeys.STATUS) ? sessionStorage.getItem(SessionKeys.STATUS) : ""],
        });

        this._el.nativeElement.querySelector("input[autofocus]").focus();
    }

    private getDataFromSession(key: string): string {
        if (sessionStorage.getItem(key)) {
            return sessionStorage.getItem(key);
        }

        return this.getForm[key].value;
    }

    private deleteProjectByIds(projectDeleteDtos: ProjectDeleteDto[]): void {
        this._projectService
            .deleteByIds(projectDeleteDtos)
            .subscribe((data: any) => {
                this._translateService
                    .get(this.i18nLabels.DELETED_SUCCESSFULLY)
                    .subscribe((text: string) => {
                        this._alertService.success(text);
                    });

                this.filterSortedProjectsInChunkAndPage();

                this.selectedProjectDeleteDtos = this.selectedProjectDeleteDtos
                                                     .filter(item => {
                                                         for (let i in projectDeleteDtos) {
                                                             if (item.id === projectDeleteDtos[i].id) {
                                                                 return false;
                                                             }
                                                         }

                                                         return true;
                                                     });
            });
    }

    changeSortOrder(event: any): void {
        let field = event.target.attributes.sortable.value;

        if (this.currentSortField === field) {
            this.isAscending = !this.isAscending;
        }
        else {
            this.currentSortField = field;
            this.isAscending = true;
        }

        this.filterSortedProjectsInChunkAndPage();
    }

    ngOnInit(): void {
        this.initForm();

        this.filterSortedProjectsInChunkAndPage();
        this.getAllProjectStatuses();
    }

    getDetailLink(projectNumber: number): string {
        return `/${AppUrl.PROJECT}/${projectNumber}`;
    }

    get getForm() {
        return this.filterProjectForm.controls;
    }

    resetForm() {
        this.page = 1;

        for (let key in this.getForm) {
            this.getForm[key].setValue("");
        }

        sessionStorage.setItem(SessionKeys.KEYWORD, "");
        sessionStorage.setItem(SessionKeys.STATUS, "");

        this.filterSortedProjectsInChunkAndPage();

        this._el.nativeElement.querySelector("input[autofocus]").focus();
    }

    onSubmitFilterProjectForm(): void {
        sessionStorage.setItem(SessionKeys.KEYWORD, this.getForm.keyword.value);
        sessionStorage.setItem(SessionKeys.STATUS, this.getForm.status.value);

        this.filterSortedProjectsInChunkAndPage();
    }

    getAllProjectStatuses(): void {
        this._projectService
            .findAllProjectStatuses()
            .subscribe((data: ProjectStatusDto[]) => {
                this.statuses = data;
            });
    }

    filterSortedProjectsInChunkAndPage(): void {
        let keyword: string = this.getDataFromSession(SessionKeys.KEYWORD);
        let status: string = this.getDataFromSession(SessionKeys.STATUS);
        this.sortOrder = new SortOrder(this.currentSortField, this.isAscending ? SortOrderType.ASC : SortOrderType.DESC);

        this._projectService
            .findSortedChunkInPageWithKeywordAndStatus(keyword, status, this.chunk, this.page, this.sortOrder)
            .subscribe((data: any) => {
                this.projectTableRowDtos = data.content;
                this.totalProject = data.totalElements;
            });
    }

    goToPage(page: number) {
        this.page = page;
        this.filterSortedProjectsInChunkAndPage();
    }

    askBeforeDeleteOne(projectTableRowDto: ProjectTableRowDto) {
        this._translateService
            .get(this.i18nLabels.YOU_WILL_DELETE_PROJECT_NAME, {name: projectTableRowDto.name})
            .subscribe((text: string) => {
                this._alertService.ask(text)
                           .then((result: SweetAlertResult) => {
                               if (result.value) {
                                   this.deleteProjectByIds([new ProjectDeleteDto(projectTableRowDto)]);
                               }
                           });
            });
    }

    askBeforeDeleteMulti() {
        this._translateService
            .get(this.i18nLabels.YOU_WILL_DELETE_SELECTED_PROJECTS)
            .subscribe((text: string) => {
                this._alertService.ask(text)
                           .then((result: SweetAlertResult) => {
                               if (result.value) {
                                   this.deleteProjectByIds(this.selectedProjectDeleteDtos);
                               }
                           });
            });
    }

    selectProject(event: any, projectTableRowDto: ProjectTableRowDto): void {
        this.selectedProjectDeleteDtos = this.selectedProjectDeleteDtos
                                             .filter(item => item.id !== projectTableRowDto.id);

        if (event.target.checked) {
            this.selectedProjectDeleteDtos.push(new ProjectDeleteDto(projectTableRowDto));
        }
    }

    isProjectSelected(id: number): boolean {
        let arr: ProjectDeleteDto[] = this.selectedProjectDeleteDtos
                                            .filter(item => item.id === id);

        return arr.length === 1;
    }

    // @formatter:on

}
