import { Component } from '@angular/core';
import {AuthorizationService} from './authorization.service';
import {Router} from '@angular/router';
import {FacebookService, LoginResponse, UIParams, UIResponse} from "ngx-facebook";
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {User} from 'app/shared/model/user.model';

@Component({
  templateUrl: './authorization.component.html',
  styleUrls: ['../styles/AdminLTE.min.css',
    '../styles/bootstrap.min.css',
    '../styles/main.css',
    '../styles/skins/_all-skins.min.css'],
  providers: [AuthorizationService]
})
export class AuthorizationComponent {

  authorizationGroup: FormGroup;
  // todo: remove hardcoded creds
  email = 'klymenko.ruslan.dev@gmail.com';
  password = 'a';
  errorMessage: string;

  constructor(private authorizationService: AuthorizationService, private router: Router, private fb: FacebookService, private formBuilder: FormBuilder) {
    this.authorizationGroup = formBuilder.group({
      'email' : [null, Validators.email],
      'password' : [null, Validators.required]
    });
  }

  login() {
    const object = this.authorizationService.login(this.email, this.password);
    object.subscribe(response => {
        localStorage.setItem('tokenType', 'local');
        localStorage.setItem('token', response.token);
        localStorage.setItem('user', JSON.stringify(response.user));
        this.router.navigate(['/']);
      },
      err => this.errorMessage = err);
    // todo: error handling, localization
  }

  loginWithFacebook() {
    this.fb.login()
      .then((response: LoginResponse) => {
        this.fb.api('/me?fields=first_name,last_name,email').then(facebookUserInfo => {
         // let name = facebookUserInfo.name;
         // let firstName = name.split(' ')[0];
         // let lastName = name.split(' ')[1];
          alert(JSON.stringify(facebookUserInfo));
        });

        localStorage.setItem('tokenType', 'fb');
        localStorage.setItem('token', response.authResponse['accessToken']);
        this.router.navigate(['/']);
      })
      .catch((error: any) => console.error(error));

  }

  redirectToRegistration() {
    this.router.navigate(['registration']);
  }

  // share(url: string) {
  //   const params: UIParams = {
  //     href: 'https://github.com/klymenko-ruslan/cryptocurrency',
  //     method: 'share'
  //   };
  //
  //   this.fb.ui(params)
  //     .then((res: UIResponse) => console.log(res))
  //     .catch((e: any) => console.error(e));
  // }
  //
  // submit(any) {
  //   alert(any);
  // }

}
