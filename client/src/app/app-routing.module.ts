import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {NewProjectComponent} from "./modules/project/pages/new-project/new-project.component";
import {MainComponent} from "./pages/main/main.component";
import {ProjectsListComponent} from "./modules/project/pages/projects-list/projects-list.component";
import {ErrorComponent} from "./pages/error/error.component";
import {AppUrl} from "./constants/app-url";
import {UpdateProjectComponent} from "./modules/project/pages/update-project/update-project.component";

const routes: Routes = [
    {path: AppUrl.HOME, redirectTo: AppUrl.PROJECT_LIST, pathMatch: "full"},
    {path: AppUrl.PROJECT, redirectTo: AppUrl.PROJECT_LIST, pathMatch: "full"},
    {path: AppUrl.ERROR, component: ErrorComponent},
    {
        path: AppUrl.HOME,
        component: MainComponent,
        children: [
            {
                path: AppUrl.PROJECT,
                children: [
                    {path: AppUrl.HOME, redirectTo: AppUrl.PROJECT_LIST, pathMatch: "full"},
                    {path: AppUrl.LIST, component: ProjectsListComponent, pathMatch: "full"},
                    {path: AppUrl.NEW, component: NewProjectComponent, pathMatch: "full"},
                    {path: AppUrl.DETAIL, component: UpdateProjectComponent, pathMatch: "full"},
                    {path: "**", redirectTo: AppUrl.PROJECT_LIST, pathMatch: "full"},
                ]
            },
        ]
    },

    {path: "**", redirectTo: AppUrl.HOME, pathMatch: "full"},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
