import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { AddProductComponent } from "./add-product/add-product.component";
import { ProductListComponent } from "./product-list/product-list.component";
import { productRoutes } from "./product.routing";
import { ProductPageModule } from "src/app/pages/product/product-page.module";

@NgModule({
  imports: [
    CommonModule,
    ProductPageModule,
    RouterModule.forChild(productRoutes),
  ],
  declarations: [AddProductComponent, ProductListComponent],
  exports: [ProductListComponent],
})
export class ProductModule {}
