import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
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
