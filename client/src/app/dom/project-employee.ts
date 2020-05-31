export class ProjectEmployee {

    projectEmployeeId: ProjectEmployeeId;

    constructor(projectId: number, employeeId: number) {
        this.projectEmployeeId = new ProjectEmployeeId(projectId, employeeId);
    }

}

class ProjectEmployeeId {

    projectId: number;

    employeeId: number;

    constructor(projectId: number, employeeId: number) {
        this.projectId = projectId;
        this.employeeId = employeeId;
    }

}
