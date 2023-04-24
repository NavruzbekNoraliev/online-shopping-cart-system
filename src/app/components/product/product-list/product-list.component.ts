import { Component, OnInit } from "@angular/core";
import { Observable, tap } from "rxjs";
import { ApiService } from "src/app/core/services/api.service";
import { CartService } from "src/app/core/services/cart.service";
import { ProductService } from "src/app/core/services/product.service";

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.css"],
})
export class ProductListComponent implements OnInit {
  public productList!: any[];
  // public filterCategory: any;
  // public searchKey: string = "";
  request$?: Observable<any>;

  constructor(
    private productAPI: ProductService,
    private apiService: ApiService,
    private cartService: CartService
  ) {}

  ngOnInit() {
    // this.apiService.getProduct().subscribe((res) => {
    //   this.productList = res;
    //   this.filterCategory = res;

    //   this.productList.forEach((a: any) => {
    //     if (
    //       a.category === "men's clothing" ||
    //       a.category === "women's clothing"
    //     ) {
    //       a.category = "fashion";
    //     }
    //     Object.assign(a, { quantity: 1, total: a.price });
    //   });
    // });

    // this.cartService.search.subscribe((value: any) => {
    //   this.searchKey = value;
    // });
    this.getAllProducts();
  }

  getAllProducts() {
    this.request$ = this.productAPI.getAllProducts().pipe(
      tap((res) => {
        console.log(res);
      })
    );
  }

  addProductToCart(){

  }
  // addToCart(item: any) {
  //   this.cartService.addToCart(item);
  // }

  // filter(category: string) {
  //   this.filterCategory = this.productList.filter((a: any) => {
  //     if (a.category == category || category == "") {
  //       return a;
  //     }
  //   });
  // }
}