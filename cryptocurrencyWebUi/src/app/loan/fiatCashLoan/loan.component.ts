

import {Component} from '@angular/core';
import {CurrencyUtil} from '../../shared/util/currency.util';
import {LoanService} from 'app/loan/fiatCashLoan/loan.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FiatCashLoan} from './loan.model';
import {OfficeService} from "../../office/office.service";
import {Address} from "../../shared/model/address.model";
import {Office} from "../../shared/model/office.model";

@Component({
  templateUrl: './loan.component.html',
  styleUrls: ['../../styles/AdminLTE.min.css',
    '../../styles/bootstrap.min.css',
    '../../styles/main.css',
    '../../styles/skins/_all-skins.min.css']
})
export class LoanComponent {

  loanGroup: FormGroup;

  creditCardNumber: string;

  loanTypes = ["EMPTY","CREDITCARD","MAIL_DELIVERY","CASH"]
  loanType: string;
  country: string;
  countries: string[];
  city: string;
  cities: string[];
  office: Office;
  offices: Office[];
  
  street: string;
  appartments: string;

  fiatCurrencies: string[];
  fiatCurrency: string;
  cryptoCurrencies: string[];
  cryptoCurrency: string;
  sum: number = 0;
  days: number = 0;
  loan = new FiatCashLoan();

  loadingConditions = false;
  loadingWalletKey = false;

  constructor(private loanService: LoanService, private formBuilder: FormBuilder, private officeService: OfficeService) {
    this.updateCountries();

    this.fiatCurrencies = CurrencyUtil.getSupportedFiatCurrencies();
    this.fiatCurrency = this.fiatCurrencies[0];
    this.cryptoCurrencies = CurrencyUtil.getSupportedCryptoCurrencies();
    this.cryptoCurrency = this.cryptoCurrencies[0];

    this.loanGroup = formBuilder.group({
      'sum' : [null, [Validators.pattern("[0-9]+"), Validators.min(1)]],
      'days' : [null, [Validators.pattern("[0-9]+"), Validators.min(1)]]
    });
  }
  onLoanTypeChange(newLoanType) {
    this.loanType = newLoanType;
  }
  onCountryChange(newCountry) {
    this.country = newCountry;
    this.updateCities();
  }
  onCityChange(newCity) {
    this.city = newCity;
    this.updateOffices();
  }
  onOfficeChange(newOfficeId) {
    for(var i = 0; i < this.offices.length; i++) {
      if(this.offices[i].id == newOfficeId) {
        this.office = this.offices[i];
        break;
      }
    }
  }
  onFiatCurrencyChange(newFiatCurrency) {
    this.fiatCurrency = newFiatCurrency;
  }
  onCryptoCurrencyChange(newCryptoCurrency) {
    this.cryptoCurrency = newCryptoCurrency;
  }

  getLoanConditions() {
    const loanObservable = this.loanService.getLoanConditions(this.fiatCurrency, this.cryptoCurrency, this.sum, this.days, this.office.id);
    this.loadingConditions = true;
    loanObservable.subscribe(loan => {
        this.loan = loan;
        this.loadingConditions = false;
      },
      err => {
        alert('err ' + err);
        this.loadingConditions = false;
      }
    );
  }

  approve() {
    this.loadingWalletKey = true;
    this.loanService.approve(this.loan.id)
      .subscribe(address => {
          this.loan.pledgeKey = address;
          this.loadingWalletKey = false;
        },
        err => {
                this.loadingWalletKey = false;
                alert(err);
              }
    );
  }

  updateCountries() {
    this.officeService.getCountries().subscribe(countries => {
      this.countries = countries;
      this.country = this.countries[0];
      this.updateCities();
    });
  }

  updateCities() {
    this.officeService.getCitiesByCountryName(this.country).subscribe(cities => {
      this.cities = cities;
      this.city = this.cities[0];
      this.updateOffices();
    });
  }

  updateOffices() {
    this.officeService.getOfficesByCountryCity(this.country, this.city).subscribe(offices => {
      this.offices = offices;
      this.office = this.offices[0];
    });
  }

}
