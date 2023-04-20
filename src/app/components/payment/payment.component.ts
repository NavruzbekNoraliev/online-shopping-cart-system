import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/core/services/cart.service';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  public grandTotal: number = 0;
  
  constructor(private cartService: CartService ) { }

    ngOnInit() {
      this.grandTotal = this.cartService.getTotalPrice();
    }

}
