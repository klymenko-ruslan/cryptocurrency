import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../authorization/authorization.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['styles/AdminLTE.min.css',
              'styles/bootstrap.min.css',
              'styles/main.css',
              'styles/skins/_all-skins.min.css']
})
export class MainComponent {

  constructor(private authorizationService: AuthorizationService) {}

  logout() {
    this.authorizationService.logout();
  }

}
