import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AuthorizationComponent } from './authorization/authorization.component';
import {AuthorizationGuardService} from './authorization/guard/authorization-guard.service';
import {AuthorizationService} from 'app/authorization/authorization.service';
import {FormsModule} from '@angular/forms';
import { routing } from './app.routing';
import {HttpModule} from '@angular/http';
import { MainComponent } from './main/main.component';
import { AppComponent } from './app.component';
import { FacebookModule } from 'ngx-facebook';

@NgModule({
  declarations: [
    AppComponent,
    AuthorizationComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    FacebookModule.forRoot(),
    routing
  ],
  providers: [AuthorizationGuardService, AuthorizationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
