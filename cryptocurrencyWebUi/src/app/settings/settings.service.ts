import {Injectable} from "@angular/core";
import {User} from '../shared/model/user.model';
import {HttpUtil} from '../shared/util/http.util';
import {Http} from '@angular/http';

@Injectable()
export class SettingsService {

  constructor(private http: Http) {}

  updateSettings(user: User) {
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.post('http://localhost:8081/user/update', JSON.stringify(user), headers)
      .map(response => response.json())
      .catch(error => HttpUtil.handleError(error));
  }
}
