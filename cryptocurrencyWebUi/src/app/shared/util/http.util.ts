import { Http,  Response, Headers } from '@angular/http';
import {Observable} from 'rxjs/Observable';

export class HttpUtil {
  public static handleError(error: Response | any) {
    if (error.status === 401) {
      localStorage.removeItem('user');
      sessionStorage.removeItem('cuid');
      return Observable.throw(error);
    } else {
      return Observable.throw(error);
    }
  }

  public static getHeaders(): Headers {
    const headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    return headers;
  }
}
