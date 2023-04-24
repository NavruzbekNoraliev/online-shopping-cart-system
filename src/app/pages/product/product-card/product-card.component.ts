import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";

@Component({
  selector: "app-product-card",
  templateUrl: "./product-card.component.html",
  styleUrls: ["./product-card.component.css"],
})
export class ProductCardComponent implements OnInit {
  @Input("product") product: any;
  @Output() addToCart = new EventEmitter<string>();
  constructor() {}

  ngOnInit() {}

  addToCartThisItem() {
    this.addToCart.emit(this.product.id);
  }
}
