export enum SortOrderType {

    ASC = "asc",

    DESC = "desc"

}

export class SortOrder {

    field: string;

    sortOrderType: SortOrderType;

    constructor(field: string, sortOrderType: SortOrderType) {
        this.field = field;
        this.sortOrderType = sortOrderType;
    }

    value(): string {
        return `${this.field}.${this.sortOrderType}`;
    }

}
