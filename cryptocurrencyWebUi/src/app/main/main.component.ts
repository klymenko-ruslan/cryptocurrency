import { Component } from '@angular/core';
import {MainService} from './main.service';
import {Value} from '../shared/model/value.model';
import {CurrencyUtil} from "../shared/util/currency.util";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['../styles/AdminLTE.min.css',
    '../styles/bootstrap.min.css',
    '../styles/main.css',
    '../styles/skins/_all-skins.min.css']
})
export class MainComponent {
  selectedCurrency: string;
  currencies: string[];
  values: Value[];
  toCurrency = 'usd';


  averagePrice: number;
  lastPrice: number;
  differencePrice: number;
  maxPrice: number;
  minPrice: number;

  constructor(private mainService: MainService) {
    this.getListOfCurrencies();
    this.getValues('day');
  }

  getListOfCurrencies() {
    this.currencies = CurrencyUtil.getSupportedCryptoCurrencies();
    this.selectedCurrency = this.currencies[0];
  }

  getValues(period: string) {
    this.mainService.getValues(period).subscribe(values => {
      this.values = values;
      this.updateStatistics(this.selectedCurrency);
    });
  }

  updateStatistics(currency) {
    this.updateAverage(currency);
    this.updateLastPrice(currency);
    this.updateDifferencePrice(currency);
    this.updateMaxPrice(currency);
    this.updateMinPrice(currency);
  }
  updateAverage(currency) {
    this.averagePrice = this.getSum(currency) / this.values.length;
  }
  updateLastPrice(currency) {
    this.lastPrice = this.values[this.values.length - 1][currency].price[this.toCurrency];
  }
  updateDifferencePrice(currency) {
    const toCurrency = this.toCurrency;
    this.differencePrice = this.values[this.values.length - 1][currency].price[toCurrency] - this.values[0][currency].price[toCurrency];
  }
  updateMaxPrice(currency) {
    let max = 0;
    this.values.forEach((value, index) => {
      const currentPrice = Number.parseFloat(value[currency].price[this.toCurrency]);
      if(!Number.isNaN(currentPrice) && currentPrice > max) {
        max = currentPrice;
      }
    });
    this.maxPrice = max;
  }
  updateMinPrice(currency) {
    let min = Number.MAX_VALUE;
    this.values.forEach((value, index) => {
      const currentPrice = Number.parseFloat(value[currency].price[this.toCurrency]);
      if(!Number.isNaN(currentPrice) && currentPrice < min) {
        min = currentPrice;
      }
    });
    this.minPrice = min;
  }
  getSum(currency) {
    let sum = 0;
    this.values.forEach((value, index) => {
      const currentPrice = Number.parseFloat(value[currency].price[this.toCurrency]);
      if(!Number.isNaN(currentPrice)) {
        sum += currentPrice;
      }
    });
    return sum;
  }

  currencyChanged(value) {
    this.updateStatistics(this.selectedCurrency);
  }
  periodChanged(value) {
    this.getValues(value);
  }
}
