import { Routes } from "@angular/router";
import { AddProductComponent } from "./add-product/add-product.component";
import { ProductListComponent } from "./product-list/product-list.component";

export const productRoutes: Routes = [
  { path: "", pathMatch: "full", redirectTo: "list" },
  { path: "add", component: AddProductComponent },
  { path: "list", component: ProductListComponent },
];
