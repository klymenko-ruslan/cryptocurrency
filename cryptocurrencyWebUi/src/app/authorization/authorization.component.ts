import { Component } from '@angular/core';
import {AuthorizationService} from './authorization.service';
import {Router} from '@angular/router';
import {FacebookService, InitParams, LoginResponse, UIParams, UIResponse} from "ngx-facebook";

@Component({
  templateUrl: './authorization.component.html',
  styleUrls: ['../styles/AdminLTE.min.css',
    '../styles/bootstrap.min.css',
    '../styles/main.css',
    '../styles/skins/_all-skins.min.css'],
  providers: [AuthorizationService]
})
export class AuthorizationComponent {

  // todo: remove hardcoded creds
  email = 'klymenko.ruslan.primary@gmail.com';
  password = 'a';

  constructor(private authorizationService: AuthorizationService, private router: Router, private fb: FacebookService) {
  }

  login() {
    const object = this.authorizationService.login(this.email, this.password);
    object.subscribe(token => {
        localStorage.setItem('tokenType', 'local');
        localStorage.setItem('token', token);
        this.router.navigate(['/']);
      },
      err => alert('err ' + err));
    // todo: error handling, localization
  }

  loginWithFacebook(): void {
    this.fb.login()
      .then((response: LoginResponse) => {
        localStorage.setItem('tokenType', 'fb');
        localStorage.setItem('token', response.authResponse['accessToken']);
        this.router.navigate(['/']);
      })
      .catch((error: any) => console.error(error));

  }

  share(url: string) {
    const params: UIParams = {
      href: 'https://github.com/klymenko-ruslan/cryptocurrency',
      method: 'share'
    };

    this.fb.ui(params)
      .then((res: UIResponse) => console.log(res))
      .catch((e: any) => console.error(e));
  }


}
