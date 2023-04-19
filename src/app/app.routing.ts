import { Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { MainComponent } from './components/main.component';
import { PaymentComponent } from './components/payment/payment.component';
import { PaymentConfirmComponent } from './pages/payment-confirm/payment-confirm.component';

export const routes: Routes = [
    { path: '', component: MainComponent },
    { path: "cart", component: CartComponent },
    { path:"payment", component: PaymentComponent },
    { path: "payment-confirm", component: PaymentConfirmComponent },
    { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)}
];

