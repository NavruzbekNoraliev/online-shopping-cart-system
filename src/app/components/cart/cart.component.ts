import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { CartService } from "src/app/core/services/cart.service";

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.scss"],
})
export class CartComponent implements OnInit {
  public productsInCart: any = [];
  public grandTotal: number = 0;
  displayedColumns: string[] = [
    "name",
    "image",
    "description",
    "price",
    "quantity",
    "total",
    "action",
  ];
  constructor(private route: Router, private cartService: CartService) {}

  ngOnInit() {
    this.cartService.getCartProducts().subscribe((res) => {
      console.log(res)
      this.productsInCart = res.cartItems;
      this.grandTotal = res.totalPrice;
    });
  }

  removeItem(cartItemId: any) {
    // this.cartService.removeCartItem(item);
    this.cartService.removeCartItem(cartItemId);
  }

  emptyCart() {
    // this.cartService.removeAllCartItems();
    this.cartService.removeAllCartItems();
  }

  navigateToMain() {
    this.route.navigateByUrl("/");
  }
  navigateToPayment() {
    this.route.navigateByUrl("payment");
  }
}
