import { Routes } from "@angular/router";
import { OnetimePaymentComponent } from "./onetime-payment/onetime-payment.component";
import { PaymentComponent } from "./payment.component";

export const paymentRoutes: Routes = [
    { path: 'onetime', component: OnetimePaymentComponent},
    { path: '', component: PaymentComponent},

]