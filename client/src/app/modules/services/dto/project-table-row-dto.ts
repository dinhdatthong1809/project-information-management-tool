import {ProjectStatusDTO} from "./project-status-dto";

export class ProjectTableRowDTO {

    ID: number;

    projectNumber: number;

    name: string;

    status: ProjectStatusDTO;

    customer: string;

    startDate: Date;

}
