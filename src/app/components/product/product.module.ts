import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { ProductListComponent } from "./product-list/product-list.component";
import { productRoutes } from "./product.routing";
import { ProductPageModule } from "src/app/pages/product/product-page.module";
import { AddProductComponent } from "./product-management/add-product/add-product.component";
import { AllMaterialModule } from "src/app/shared/material.module";
import { FlexLayoutModule } from "@angular/flex-layout";
import { ReactiveFormsModule } from "@angular/forms";
import { ApproveProductComponent } from "./product-management/approve-product/approve-product.component";
import { ListProductComponent } from "./product-management/list-product/list-product.component";

@NgModule({
  imports: [
    CommonModule,
    ProductPageModule,
    AllMaterialModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    RouterModule.forChild(productRoutes),
  ],
  declarations: [AddProductComponent,ApproveProductComponent, ListProductComponent, ProductListComponent],
  exports: [ProductListComponent],
})
export class ProductModule {}
