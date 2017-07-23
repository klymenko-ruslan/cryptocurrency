import { Component, OnInit } from '@angular/core';
import {FacebookService, InitParams} from 'ngx-facebook';

@Component({
  selector: 'app',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  ngOnInit(): void {
  }

  constructor(private fb: FacebookService) {

    const initParams: InitParams = {
      appId: '1234566778',
      xfbml: true,
      version: 'v2.8'
    };

    fb.init(initParams);



  }
