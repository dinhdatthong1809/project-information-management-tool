import {ProjectEmployee} from "./project-employee";
import {ProjectStatusDto} from "../modules/services/dto/project-status-dto";

export enum ProjectStatus {

    NEW = "NEW",

    PLA = "PLA",

    INP = "INP",

    FIN = "FIN"

}

export class Project {

    id: number;

    companyGroupId: number;

    projectNumber: number;

    name: string;

    customer: string;

    status: string | ProjectStatusDto;

    startDate: string;

    endDate: string;

    projectEmployees: ProjectEmployee[];

    version: number;

    constructor(formValues: any) {
        this.id = formValues.id;
        this.companyGroupId = formValues.companyGroupId;
        this.projectNumber = formValues.projectNumber;
        this.name = formValues.name;
        this.customer = formValues.customer;
        this.status = formValues.status;
        this.startDate = formValues.startDate;
        this.endDate = formValues.endDate;

        if (formValues.projectEmployees === null) {
            return;
        }

        this.projectEmployees = formValues.projectEmployees
                                          .map(employeeId => new ProjectEmployee(this.id, employeeId));

        this.version = formValues.version;
    }

}
