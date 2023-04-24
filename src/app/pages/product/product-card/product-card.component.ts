import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { ApiService } from "src/app/core/services/api.service";
import { CartService } from "src/app/core/services/cart.service";
import { BehaviorSubject, Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { ProductService } from "src/app/core/services/product.service";

@Component({
  selector: "app-product-card",
  templateUrl: "./product-card.component.html",
  styleUrls: ["./product-card.component.css"],
})
export class ProductCardComponent implements OnInit {
  @Input("product") product: any;
  request$?: Observable<any>;
  private requesting: BehaviorSubject<any> = new BehaviorSubject(false);
  requesting$: Observable<boolean> = this.requesting.asObservable();
  @Output() addToCart = new EventEmitter<string>();

  constructor() {}

  ngOnInit() {}

  addToCartThisItem(item: any) {
    this.addToCart.emit(item);
  }
}
