export class ApiUrl {

    private static readonly PROTOCOL: string = "http"
    private static readonly ROOT: string = "localhost:8080/api";
    private static readonly API_VERSION: string = "1"
    private static readonly FULL_API_LINK: string = `${ApiUrl.PROTOCOL}://${ApiUrl.ROOT}/v${ApiUrl.API_VERSION}`;

    static readonly PROJECT: string = `${ApiUrl.FULL_API_LINK}/projects`;
    static readonly PROJECT_STATUSES: string = `${ApiUrl.PROJECT}/statuses`;

    static readonly COMPANY_GROUP: string = `${ApiUrl.FULL_API_LINK}/company-groups`;

    static readonly EMPLOYEE: string = `${ApiUrl.FULL_API_LINK}/employees`;

}
