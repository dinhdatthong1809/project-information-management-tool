import {AbstractService} from "../../services/abstract-service";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {ProjectStatusDto} from "./dto/project-status-dto";
import {Project} from "../../dom/project";
import {Injectable} from "@angular/core";
import {ServicesModule} from "./services.module";
import {ApiUrl} from "../../constants/api-url";
import {ProjectDeleteDto} from "./dto/project-delete-dto";
import {SortOrder} from "../../enums/sort-order";

@Injectable({
    providedIn: ServicesModule
})
export class ProjectService extends AbstractService {

    // @formatter:off

    findAllProjectStatuses(): Observable<ProjectStatusDto[]> {
        return super.get<ProjectStatusDto[]>(ApiUrl.PROJECT_STATUSES)
                    .pipe(catchError(super.handleError));
    }

    findSortedChunkInPageWithKeywordAndStatus(keyword: string, status: string, chunk: number, page: number, sortOder: SortOrder): Observable<any> {
        return super.get<any>(`${ApiUrl.PROJECT}?keyword=${keyword}&status=${status}&chunk=${chunk}&page=${page}&order=${sortOder.value()}`)
                    .pipe(catchError(super.handleError));
    }

    findByProjectNumber(projectNumber: number): Observable<Project> {
        return super.get<Project>(`${ApiUrl.PROJECT}/${projectNumber}`);
    }

    save(project: Project): Observable<any> {
        return super.post<Project, any>(ApiUrl.PROJECT, project);
    }

    update(project: Project): Observable<Project> {
        return super.put<Project, Project>(ApiUrl.PROJECT, project);
    }

    deleteByIds(projectDeleteDtos: ProjectDeleteDto[]): Observable<any> {
        return super.delete<ProjectDeleteDto[], any>(ApiUrl.PROJECT, projectDeleteDtos)
                    .pipe(catchError(super.handleError));
    }

    // @formatter:on

}
