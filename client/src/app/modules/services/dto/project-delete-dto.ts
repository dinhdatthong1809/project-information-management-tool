import {ProjectTableRowDto} from "./project-table-row-dto";

export class ProjectDeleteDto {

    id: number;

    projectNumber: number;

    status: string;

    version: number;

    constructor(projectTableRowDto: ProjectTableRowDto) {
        this.id = projectTableRowDto.id;
        this.projectNumber = projectTableRowDto.projectNumber;
        this.status = projectTableRowDto.status.id;
        this.version = projectTableRowDto.version;
    }

}
