

import {AfterViewInit, Component, ElementRef} from '@angular/core';

@Component({
  templateUrl: './loan.component.html',
  styleUrls: ['../styles/AdminLTE.min.css',
    '../styles/bootstrap.min.css',
    '../styles/main.css',
    '../styles/skins/_all-skins.min.css']
})
export class LoanComponent implements AfterViewInit {

  constructor(private elRef: ElementRef) {

  }

  ngAfterViewInit() {
    const elements = this.elRef.nativeElement.getElementsByClassName('row');
    alert(elements.length);
    elements.forEach((item => {
      if(item.name === 'linkLoans') {
        item.addClassName('active');
      } else {
        item.removeClass('active');
      }
    }));
  }

}
