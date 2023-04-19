import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment-confirm',
  templateUrl: './payment-confirm.component.html',
  styleUrls: ['./payment-confirm.component.css']
})
export class PaymentConfirmComponent implements OnInit {
  constructor(private route: Router) {

  }

  ngOnInit(): void {
      
  }

  navigateToMain() {
    this.route.navigateByUrl("/");
  }

}
