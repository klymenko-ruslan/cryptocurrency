
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {HttpUtil} from '../shared/util/http.util';
import {Value} from '../shared/model/value.model';
import {CurrencyUtil} from "../shared/util/currency.util";

@Injectable()
export class MainService {
  constructor(private http: Http) { }

  getListOfCurrencies(): Observable<Array<string>> {
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.get('http://localhost:8083/value/currencies')
      .map(response => response.json())
      .catch(error => HttpUtil.handleError(error));
  }
  getValues(period): Observable<Value[]> {
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.get('http://localhost:8083/value/' + period)
      .map(response => this.toValues(response.json()))
      .catch(error => HttpUtil.handleError(error));
  }
  private toValues(response: any): Value[] {
    const values: Value[] = [];
    response.forEach((item, index) => {
      const value = new Value();
      CurrencyUtil.getSupportedCryptoCurrencies().forEach((currency) => {
        value[currency] = item[currency];
      })
      value.date = item.date;
      values.push(value);
    });
    return values;
  }
}
