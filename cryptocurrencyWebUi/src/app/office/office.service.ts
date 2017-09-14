
import {Http} from '@angular/http';
import {HttpUtil} from '../shared/util/http.util';
import {Injectable} from "@angular/core";
import {Office} from "../shared/model/office.model";

@Injectable()
export class OfficeService {
  constructor(private http: Http) { }

  getCountries() {
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.get('http://localhost:8081/office/countries', headers)
              .map(response => response.json())
  }

  getCitiesByCountryName(countryName: string) {
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.get('http://localhost:8081/office/cities/' + countryName, headers)
      .map(response => response.json())
  }

  getOfficesByCountryCity(countryName: string, cityName: string) {
    const headers = {headers: HttpUtil.getHeaders()};
    return this.http.get('http://localhost:8081/office/get-by-country-city?countryName=' + countryName + "&cityName=" + cityName, headers)
                .map(response => {
                  let json = response.json();
                  let offices: Office[] = [];
                  for(var i = 0; i < json.length; i++) {
                    let office = Office.toOffice(json[i]);
                    offices.push(office);
                  }
                  return offices;
                });
  }
}
