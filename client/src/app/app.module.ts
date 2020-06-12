import {BrowserModule} from "@angular/platform-browser";
import {ErrorHandler, NgModule} from "@angular/core";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {NavBarComponent} from "./components/nav-bar/nav-bar.component";
import {SideBarComponent} from "./components/side-bar/side-bar.component";
import {ProjectModule} from "./modules/project/project.module";
import {MainComponent} from "./pages/main/main.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgbDateAdapter, NgbDateParserFormatter, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {CustomNgbDateAdapterService} from "./services/custom-ngb-date-adapter.service";
import {CustomNgbDateParserFormatterService} from "./services/custom-ngb-date-parser-formatter.service";
import {ErrorsHandlerService} from "./services/errors-handler.service";
import {ErrorComponent} from './pages/error/error.component';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";

@NgModule({
    declarations: [
        AppComponent,
        NavBarComponent,
        SideBarComponent,
        MainComponent,
        ErrorComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        ProjectModule,
        BrowserAnimationsModule,
        NgbModule,
        AppRoutingModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: (httpClient: HttpClient) => new TranslateHttpLoader(httpClient),
                deps: [HttpClient]
            }
        })
    ],
    providers: [
        {provide: ErrorHandler, useClass: ErrorsHandlerService},
        {provide: NgbDateAdapter, useClass: CustomNgbDateAdapterService},
        {provide: NgbDateParserFormatter, useClass: CustomNgbDateParserFormatterService}
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule {

}
