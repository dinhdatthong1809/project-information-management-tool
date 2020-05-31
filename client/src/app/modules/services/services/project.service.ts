import {AbstractService} from "../../../services/abstract-service";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {ProjectStatusDTO} from "../dto/project-status-dto";
import {Project} from "../../../dom/project";
import {Injectable} from "@angular/core";
import {ServicesModule} from "../services.module";
import {ApiUrl} from "../../../constants/api-url";

@Injectable({
    providedIn: ServicesModule
})
export class ProjectService extends AbstractService {

    // @formatter:off

    findAllProjectStatuses(): Observable<ProjectStatusDTO[]> {
        return super.get<ProjectStatusDTO[]>(ApiUrl.PROJECT_STATUSES)
                    .pipe(catchError(super.handleError));
    }

    filterWithKeywordAndStatusInChunkAndPage(keyword: string, status: string, chunk: number, page: number): Observable<any> {
        return super.get<any>(`${ApiUrl.PROJECT}?keyword=${keyword}&status=${status}&chunk=${chunk}&page=${page}`)
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

    deleteByIds(ids: number[]): Observable<any> {
        return super.delete<number[], any>(ApiUrl.PROJECT, ids)
                    .pipe(catchError(super.handleError));
    }

    // @formatter:on

}
