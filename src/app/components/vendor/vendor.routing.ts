import { Routes } from "@angular/router";
import { ApproveVendorsComponent } from "./approve-vendors/approve-vendors.component";
import { ListVendorsComponent } from "./list-vendors/list-vendors.component";

export const vendorRoutes: Routes = [
    { path: '', component: ListVendorsComponent},
    { path: 'approve', component: ApproveVendorsComponent },
]