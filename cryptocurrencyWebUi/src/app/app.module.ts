import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AuthorizationComponent } from './authorization/authorization.component';
import {AuthorizationGuardService} from './authorization/guard/authorization-guard.service';
import {AuthorizationService} from 'app/authorization/authorization.service';
import {FormsModule} from '@angular/forms';
import { routing } from './app.routing';
import { HttpModule, Http } from '@angular/http';
import { MainComponent } from './main/main.component';
import { AppComponent } from './app.component';
import { FacebookModule } from 'ngx-facebook';
import { TranslateModule, TranslateLoader} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import {MainService} from './main/main.service';
import {LoanComponent} from './loan/loan.component';

export function createTranslateLoader(http: Http) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    AuthorizationComponent,
    MainComponent,
    LoanComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [Http]
      }
    }),
    FacebookModule.forRoot(),
    routing
  ],
  providers: [AuthorizationGuardService, AuthorizationService, MainService],
  bootstrap: [AppComponent]
})
export class AppModule { }
