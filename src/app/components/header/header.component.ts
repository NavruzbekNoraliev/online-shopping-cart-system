import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { CartService } from 'src/app/service/cart/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public totalItems: number = 0;
  public searchTerm !: string;

  constructor(private route: Router, private cartService: CartService) {
  
  }

  ngOnInit(): void {
      this.cartService.getProducts().subscribe(res => {
          this.totalItems = res.length;
      });
  }

  search(event: any) {
    this.searchTerm = (event.target as HTMLInputElement).value;
    this.cartService.search.next(this.searchTerm);
  }

  navigateToMain() {
    this.route.navigateByUrl("/");
  }

  navigateToUserDashboard() {
    this.route.navigateByUrl("/");
  }

  navigateToCart() {
    this.route.navigateByUrl("cart");
  }



}
