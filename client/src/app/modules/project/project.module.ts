import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {NewProjectComponent} from "./pages/new-project/new-project.component";
import {ProjectsListComponent} from "./pages/projects-list/projects-list.component";
import {ReactiveFormsModule} from "@angular/forms";
import {ServicesModule} from "../services/services.module";
import {NgbAlertModule, NgbDatepickerI18n, NgbDatepickerModule, NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import {NgSelectModule} from "@ng-select/ng-select";
import {NgpSortModule} from "ngp-sort-pipe";
import {UpdateProjectComponent} from './pages/update-project/update-project.component';
import {AppRoutingModule} from "../../app-routing.module";
import {SharedModule} from "../shared/shared.module";
import {CustomDatePickerI18nService} from "../services/services/custom-date-picker-i18n.service";

@NgModule({
    declarations: [NewProjectComponent, ProjectsListComponent, UpdateProjectComponent],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ServicesModule,
        NgbDatepickerModule,
        NgbPaginationModule,
        NgbAlertModule,
        NgSelectModule,
        NgpSortModule,
        AppRoutingModule,
        SharedModule
    ],
    providers: [
        {provide: NgbDatepickerI18n, useClass: CustomDatePickerI18nService}
    ]
})
export class ProjectModule {

    constructor() {

    }

}
