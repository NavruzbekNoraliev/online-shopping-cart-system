import { Component, OnInit } from "@angular/core";
import { FormControl } from "@angular/forms";
import { ActivatedRoute, Route, Router } from "@angular/router";
import { BehaviorSubject, Observable, startWith, map } from "rxjs";
import { tap } from "rxjs/operators";
import { AuthService } from "src/app/auth/auth.service";
import { CartService } from "src/app/core/services/cart.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.scss"],
})
export class HeaderComponent implements OnInit {
  myControl = new FormControl("");
  options: string[] = ["One", "Two", "Three"];
  filteredOptions!: Observable<string[]>;
  public totalItem: number = 0;
  request$?: Observable<any>;
  constructor(
    private router: Router,
    private authService: AuthService,
    private cartService: CartService
  ) {
   
    // this.cartService.getCartProducts().subscribe(res => {
    //   let total = 0;
    //     res.cartItems.forEach((item: any) => {
    //       total += item.quantity;
    //     });
    //   this.totalItem = total
    // })

  }
  role = this.authService.roleType;
  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(""),
      map((value) => this._filter(value || ""))
    );
    this.cartService.getCartProducts().subscribe();
    this.request$ =this.cartService.totalItems.pipe(tap(res => {
      this.totalItem = res
    }))

  
  }

  redirectToCart() {
    this.router.navigate(["/cart"]);
  }
  logOut() {
    this.authService.logout();
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter((option) =>
      option.toLowerCase().includes(filterValue)
    );
  }
}
