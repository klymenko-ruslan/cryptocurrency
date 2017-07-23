import { Injectable } from '@angular/core';
import { Http,  Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import {Router} from '@angular/router';

@Injectable()
export class AuthorizationService {
  constructor(private http: Http, private router: Router) { }

  login(username: string, password: string): Observable<string> {
    const body = JSON.stringify({ username: username, password: password });
    const headers = {headers: this.getHeaders()};
    return this.http.post('http://localhost:8081/auth', body, headers)
      .map(response => response.json().token)
      .catch(error => this.handleError(error));
  }

  loginFb(): Observable<Object> {
    const headers = {headers: this.getHeaders()};
    return this.http.post('http://localhost:8081/connect/facebook', JSON.stringify({}), headers)
      .map(response => JSON.stringify(response.json()))
      .catch(error => this.handleError(error));


  }

  fb(): Observable<Object> {
    const headers = {headers: this.getHeaders()};
    return this.http.get('http://localhost:8081/fb', headers)
      .map(response => JSON.stringify(response.json()))
      .catch(error => this.handleError(error));


  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  private handleError(error: Response | any) {
    if (error.status === 401) {
      localStorage.removeItem('user');
      sessionStorage.removeItem('cuid');
      return Observable.throw(error);
    } else {
      return Observable.throw(error);
    }
  }

  private getHeaders(): Headers {
    const headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    return headers;
  }
}
