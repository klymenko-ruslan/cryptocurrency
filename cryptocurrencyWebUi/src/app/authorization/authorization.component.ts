import { Component } from '@angular/core';
import {AuthorizationService} from './authorization.service';
import {Router} from '@angular/router';

@Component({
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css'],
  providers: [AuthorizationService]
})
export class AuthorizationComponent {
  username = 'a';
  password = 'a';

  constructor(private authorizationService: AuthorizationService, private router: Router) {
  }

  login() {
    const object = this.authorizationService.login(this.username, this.password);
    object.subscribe(token => {
        localStorage.setItem('token', token);
        this.router.navigate(['/']);
      },
      err => alert('err ' + err));
    // todo: error handling, localization
  }

  loginFb() {
    const object = this.authorizationService.fb();
    object.subscribe(token => {
        alert(token);
        // localStorage.setItem('token', token);
        this.router.navigate(['/']);
      },
      err => alert('err ' + err));
    // todo: error handling, localization
  }

  loginFb2() {
    FB.login(function(result) {
      this.loged = true;
      this.token = result;
    }, { scope: 'user_friends' });
  }

  me() {
    FB.api('/me?fields=id,name,first_name,gender,picture.width(150).height(150),age_range,friends',
      function(result) {
        if (result && !result.error) {
          this.user = result;
          console.log(this.user);
        } else {
          console.log(result.error);
        }
      });
  }

}
