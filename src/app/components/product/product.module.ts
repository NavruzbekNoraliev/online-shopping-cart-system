import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { AddProductComponent } from "./product-management/add-product/add-product.component";
import { ProductListComponent } from "./product-list/product-list.component";
import { productRoutes } from "./product.routing";
import { ListProductComponent } from "./product-management/list-product/list-product.component";
import { AllMaterialModule } from "src/app/shared/material.module";
import { ReactiveFormsModule } from "@angular/forms";
import { FlexLayoutModule } from "@angular/flex-layout";
import { ApproveProductComponent } from "./product-management/approve-product/approve-product.component";

@NgModule({
  imports: [
    CommonModule,
    AllMaterialModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    RouterModule.forChild(productRoutes),
  ],
  declarations: [
    AddProductComponent,
    ProductListComponent,
    ApproveProductComponent,
    ListProductComponent,
  ],
})
export class ProductModule {}
