import {Component} from '@angular/core';
import {CurrencyUtil} from '../shared/util/currency.util';
import {Router} from '@angular/router';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {RegistrationService} from "./registration.service";
import {TranslateService} from '@ngx-translate/core';
import {User} from '../shared/model/user.model';

@Component({
  templateUrl: './registration.component.html',
  styleUrls: ['../styles/AdminLTE.min.css',
    '../styles/bootstrap.min.css',
    '../styles/main.css',
    '../styles/skins/_all-skins.min.css']
})
export class RegistrationComponent {

  registerGroup: FormGroup;

  message: string;

  user = new User();

  constructor(private router: Router, private formBuilder: FormBuilder, private registrationService: RegistrationService, private translate: TranslateService) {
    this.registerGroup = formBuilder.group({
      'email' : [null, Validators.email],
      'password' : [null, Validators.required]
    });
  }

  register() {
    this.registrationService.register(this.user)
      .map(response => this.setMessage('REGISTRATION.USER_CREATED'))
      .catch(error => {
        this.setMessage('REGISTRATION.USER_CONFLICT');
        return error;
      }).subscribe();
  }
  setMessage(message: string) {
    this.translate.get(message).subscribe((res: string) => {
      this.message = res;
    })
  }

  redirectToAuthorize() {
    this.router.navigate(['login']);
  }
}
