import { Component } from '@angular/core';
import {FacebookService, InitParams, LoginResponse} from 'ngx-facebook';
import {TranslateService} from '@ngx-translate/core';
import {AuthorizationService} from './authorization/authorization.service';

@Component({
  selector: 'app',
  templateUrl: './app.component.html',
  styleUrls: ['./styles/AdminLTE.min.css',
    './styles/bootstrap.min.css',
    './styles/main.css',
    './styles/skins/_all-skins.min.css']
})
export class AppComponent {

  constructor(private fb: FacebookService, private translate: TranslateService, private authorizationService: AuthorizationService) {

    console.log('Initializing Facebook');

    fb.init({
      appId: '229433700788344',
      version: 'v2.9'
    });

    translate.addLangs(['en', 'ru']);
    translate.setDefaultLang('en');

    const browserLang = translate.getBrowserLang();
    translate.use(browserLang.match(/en|ru/) ? browserLang : 'en');
  }

  logout() {
    this.authorizationService.logout();
    return false;
  }
}
