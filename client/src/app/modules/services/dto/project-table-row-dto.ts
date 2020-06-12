import {ProjectStatusDto} from "./project-status-dto";

export class ProjectTableRowDto {

    id: number;

    projectNumber: number;

    name: string;

    status: ProjectStatusDto;

    customer: string;

    startDate: Date;

    version: number;

}
