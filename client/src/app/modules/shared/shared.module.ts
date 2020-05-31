import {NgModule} from '@angular/core';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {CommonModule} from "@angular/common";
import {HttpClient} from "@angular/common/http";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        TranslateModule.forChild({
            loader: {
                provide: TranslateLoader,
                useFactory: (httpClient: HttpClient) => new TranslateHttpLoader(httpClient),
                deps: [HttpClient]
            }
        }),
    ],
    exports: [
        CommonModule,
        TranslateModule
    ]
})
export class SharedModule {

}
