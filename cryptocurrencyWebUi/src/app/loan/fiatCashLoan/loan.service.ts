

import {Http} from '@angular/http';
import {HttpUtil} from '../../shared/util/http.util';
import {Observable} from 'rxjs/Observable';
import {FiatCashLoan} from 'app/loan/fiatCashLoan/loan.model';
import {Injectable} from '@angular/core';

@Injectable()
export class LoanService {
  constructor(private http: Http) {

  }

  getLoanConditions(fiatCurrency: string,  cryptoCurrency: string, sum: number, days: number, officeId: string): Observable<FiatCashLoan> {
    alert(officeId);
    const body = JSON.stringify({ periodInDays: days, currency: fiatCurrency, pledgeCurrency: cryptoCurrency, sum: sum , officeId: officeId});
    const headers = {headers: HttpUtil.getHeaders()};
    const result = this.http.post('http://localhost:8081/loan/initFiatCashLoan', body, headers)
      .map(response => this.toLoan(response.json()))
      .catch(error => HttpUtil.handleError(error));
    return result;
  }
  private toLoan(response: any): FiatCashLoan {
    let loan = new FiatCashLoan();
    loan.id = response.id;
    loan.userId = response.userId;
    loan.periodInDays = response.periodInDays;
    loan.dateCreated = this.dateToString(response.dateCreated);
    loan.dateDue = this.dateToString(response.dateDue);
    loan.pledgeSum = response.pledgeSum;
    loan.pledgeCurrency = response.pledgeCurrency;
    loan.currency = response.currency;
    loan.sum = response.sum;
    loan.returnSum = response.returnSum;
    return loan;
  }
  private dateToString(date) {
    return date.dayOfMonth + "." + date.monthValue + "." + date.year;
  }
  //
  // getCryptoAddress(loanId: string) {
  //   const headers = {headers: HttpUtil.getHeaders()};
  //   this.http.get('http://localhost:8084/loan/initFiatCashLoan', headers)
  //     .map(response => this.toLoan(response.json()))
  // }

  approve(loanId) {
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.get('http://localhost:8081/loan/approve/'+loanId, headers).map(response => response.text());
  }
}
