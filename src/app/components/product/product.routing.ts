import { Routes } from "@angular/router";
import { AddProductComponent } from "./product-management/add-product/add-product.component";
import { ProductListComponent } from "./product-list/product-list.component";
import { ListProductComponent } from "./product-management/list-product/list-product.component";
import { ApproveVendorsComponent } from "../vendor/approve-vendors/approve-vendors.component";
import { ApproveProductComponent } from "./product-management/approve-product/approve-product.component";

export const productRoutes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'list' },
    { path: 'add', component: AddProductComponent },
    { path: 'list', component: ProductListComponent },
    {
        path: 'management', children: [
            { path: '', pathMatch: 'full', redirectTo: "list" },
            { path: "approve", component: ApproveProductComponent },
            { path: "list", component: ListProductComponent },
            { path: "add", component: AddProductComponent },
            { path: ":id", component: AddProductComponent }
        ]
    }
]