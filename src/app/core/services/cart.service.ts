import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { Router } from "@angular/router";
import { CoreHTTPService } from "./base";
import { tap } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class CartService extends CoreHTTPService {
  public cartItemList: any = new BehaviorSubject<any>([]);
  public search = new BehaviorSubject<string>("");

  constructor(protected override http: HttpClient, private router: Router) {
    super(http);
  }

  setProduct(product: any) {
    this.cartItemList.push(...product);
  }

  addToCart(product: any) {
    return this.post("cart", product);
  }

  set totalItems(value: any | null) {
    if (value === null) {
      value = "";
    }
    localStorage.setItem("cartItemsNumber", value);
  }

  get totalItems(): any | null {
    return localStorage.getItem("cartItemsNumber");
  }

  getTotalPrice(): number {
    let grandTotal = 0;
    this.cartItemList.map((a: any) => {
      grandTotal += a.total;
    });
    return grandTotal;
  }

  removeCartItem(product: any) {
    this.cartItemList.map((a: any, index: any) => {
      if (product.id === a.id) {
        this.cartItemList.splice(index, 1);
      }
    });
  }

  removeAllCartItems() {
    this.cartItemList = [];
  }
}
