import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { ProductService } from 'src/app/core/services/products.service';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.scss']
})
export class ListProductComponent implements OnInit {

  constructor(
    private authAPI: AuthService,
    private router: Router,
    private productAPI: ProductService) { }

  request$?: Observable<any>;
  products!:any[];
  
  displayedColumns: string[] = ['name', 'description', 'color', 'categoryId', 'imageUrl', 'price', 'quantity' ];
  ngOnInit() {
    this.request$ = this.productAPI.getAllProductsByVendor(this.authAPI.roleId).pipe(tap(res => {
      this.products = res.content;
    }))
  }

  updateProduct(id: string) {
    this.router.navigate(['product', 'management', id]);
  }


}
