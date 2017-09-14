
import {Component} from '@angular/core';
import {User} from '../shared/model/user.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CurrencyUtil} from '../shared/util/currency.util';
import {HttpUtil} from '../shared/util/http.util';
import {SettingsService} from "./settings.service";
import {Router} from '@angular/router';

@Component({
  templateUrl: './settings.component.html',
  styleUrls: ['../styles/AdminLTE.min.css',
    '../styles/bootstrap.min.css',
    '../styles/main.css',
    '../styles/skins/_all-skins.min.css']
})
export class SettingsComponent {

  settingsGroup: FormGroup;

  message: string;

  user: User;

  fiatCurrencies: string[];

  constructor(private formBuilder: FormBuilder, private settingsService: SettingsService, private router: Router) {
    this.fiatCurrencies = CurrencyUtil.getSupportedFiatCurrencies();
    this.user = User.toUser(HttpUtil.getUserJson());
    this.settingsGroup = formBuilder.group({
      'email' : [null, Validators.email],
      'password' : [null, Validators.required],
      'newPassword' : [null, Validators.required],
      'firstName' : [null, Validators.required],
      'lastName' : [null, Validators.required],
      'profession' : [null, Validators.required]
    });
  }

  onChangeCurrency(selectedCurrency) {
    this.user.fiatCurrency = selectedCurrency;
  }

  updateSettings() {
    this.settingsService.updateSettings(this.user).subscribe(response => {
      localStorage.setItem('user', JSON.stringify(response));
      this.router.navigate(["/"]);
    });
  }

}
