import {Component, OnInit} from "@angular/core";
import {AppUrl} from "../../constants/app-url";
import {I18nLabels} from "../../i18n/i18n-labels";
import {MenuGroup} from "../../abstract/menu";

@Component({
    selector: "app-side-bar",
    templateUrl: "./side-bar.component.html",
    styleUrls: ["./side-bar.component.css"]
})
export class SideBarComponent implements OnInit {

    menuGroups: MenuGroup[] = [
        {
            header: {
                name: I18nLabels.PROJECTS_LIST,
                url: `/${AppUrl.PROJECT_LIST}`
            },
            items: []
        },
        {
            header: {
                name: I18nLabels.NEW,
                url: `/${AppUrl.PROJECT_NEW}`
            },
            items: [
                {
                    name: I18nLabels.PROJECT,
                    url: `/${AppUrl.PROJECT_NEW}`,
                },
                {
                    name: I18nLabels.CUSTOMER,
                    url: `/${AppUrl.CUSTOMER_NEW}`,
                    disabled: true
                },
                {
                    name: I18nLabels.SUPPLIER,
                    url: `/${AppUrl.SUPPLIER_NEW}`,
                    disabled: true
                }
            ]
        },
    ];

    constructor() {

    }

    ngOnInit(): void {

    }

}
