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
  public totalItems = new BehaviorSubject<number>(0)

  constructor(protected override http: HttpClient, private router: Router) {
    super(http);
    this.getCartProducts();
  }



  setProduct(product: any) {
    this.cartItemList.push(...product);
  }

  addToCart(product: any) {
    return this.post("cart", product).pipe(tap(res => {
      let total = 0;
      res.cartItems.forEach((item: any) => {
        total += item.quantity;
      });
      this.totalItems.next(total)
    }))
  }

  getCartProducts() {
    return this.get("cart").pipe(tap(res => {
      let total = 0;
      res.cartItems.forEach((item: any) => {
        total += item.quantity;
      });
      this.totalItems.next(total)
    }))
  }

  getTotalPrice(): number {
    let grandTotal = 0;
    this.cartItemList.map((a: any) => {
      grandTotal += a.total;
    });
    return grandTotal;
  }

  removeCartItem(productId: any) {
    // this.cartItemList.map((a: any, index: any) => {
    //   if (product.id === a.id) {
    //     this.cartItemList.splice(index, 1);
    //   }
    // });
    this.http.delete("cart/" + productId).subscribe(res => {
      console.log(res);
    })
  }

  removeAllCartItems() {
    // this.cartItemList = [];
    this.http.delete("cart").subscribe(res => {
      console.log(res);
    })
  }
}
