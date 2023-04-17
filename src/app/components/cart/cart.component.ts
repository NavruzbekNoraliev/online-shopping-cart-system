import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/core/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public productsInCart: any = [];
  public grandTotal: number = 0;

  constructor(private route: Router, private cartService: CartService) { }

  ngOnInit() {
    this.cartService.getProducts().subscribe(res => {
      this.productsInCart = res;
      this.grandTotal = this.cartService.getTotalPrice();
    })
  }

  removeItem(item: any) {
    this.cartService.removeCartItem(item);
  }

  emptyCart() {
    this.cartService.removeAllCartItems();
  }

  navigateToMain() {
    this.route.navigateByUrl("/");
  }

}
