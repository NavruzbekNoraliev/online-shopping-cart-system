import { Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { UserSignupComponent } from "./register/user-signup/user-signup.component";
import { VendorSignupComponent } from "./register/vendor-signup/vendor-signup.component";
import { VendorDashboardComponent } from "../components/dashboard/vendor-dashboard/vendor-dashboard.component";

export const authRoutes: Routes = [
    { path: '', pathMatch:'full', redirectTo: 'login'},
    { path: 'login', component: LoginComponent },
    { path: 'customer-register', component: UserSignupComponent },
    { path: 'vendor-register', component: VendorSignupComponent},
    { path: "vendor-dashboard", component: VendorDashboardComponent },
]