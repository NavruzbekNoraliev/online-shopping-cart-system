import { Component, OnInit } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { ProductService } from 'src/app/core/services/products.service';

@Component({
  selector: 'app-approve-product',
  templateUrl: './approve-product.component.html',
  styleUrls: ['./approve-product.component.css']
})
export class ApproveProductComponent implements OnInit {

  constructor(private productAPI: ProductService) { }
  request$?: Observable<any>;
  approvalPendingProducts = []
  displayedColumns: string[] = ['name', 'description', 'color', 'categoryId', 'imageUrl', 'price', 'quantity', 'approve'];
  ngOnInit() {
    this.getAll();
  }


  getAll() {
    this.request$ = this.productAPI.getAllPendingApprovalProducts().pipe(tap(res => {
      this.approvalPendingProducts = res.content
    }))
  }

  approve(id: string) {
    this.request$ = this.productAPI.approveProductAvailability(id).pipe(tap(_ => {
      this.approvalPendingProducts = this.approvalPendingProducts.filter((p:any) => p.id != id)
    }))
  }

}
