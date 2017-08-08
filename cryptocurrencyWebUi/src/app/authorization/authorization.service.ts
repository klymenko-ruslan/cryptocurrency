import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import {Router} from '@angular/router';
import {FacebookService, LoginResponse} from 'ngx-facebook';
import {HttpUtil} from '../shared/util/http.util';

@Injectable()
export class AuthorizationService {
  constructor(private http: Http, private router: Router, private fb: FacebookService) { }

  login(email: string, password: string): Observable<string> {
    const body = JSON.stringify({ email: email, password: password });
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.post('http://localhost:8081/auth', body, headers)
      .map(response => response.json().token)
      .catch(error => HttpUtil.handleError(error));
  }

  loginWithFacebook(): void {

    this.fb.login()
      .then((response: LoginResponse) => console.log(response))
      .catch((error: any) => console.error(error));

  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
    return false;
  }
}
