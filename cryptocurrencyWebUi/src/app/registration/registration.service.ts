
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {HttpUtil} from '../shared/util/http.util';
import {User} from "../shared/model/user.model";
import {TranslateService} from "@ngx-translate/core";

@Injectable()
export class RegistrationService {
  constructor(private http: Http) { }

  register(user: User) {
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.post('http://localhost:8081/user/create', JSON.stringify(user), headers)
  }
}
