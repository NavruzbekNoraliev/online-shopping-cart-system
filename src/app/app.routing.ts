import { Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { MainComponent } from './components/main.component';
import { PaymentComponent } from './components/payment/payment.component';
import { PaymentConfirmComponent } from './pages/payment-confirm/payment-confirm.component';
import { FullComponent } from './components/layouts/full/full.component';

export const routes: Routes = [
    {
        path: '',
        component: FullComponent,
        children: [{ 
            path: '', 
            redirectTo: '/main', 
            pathMatch: 'full' 
          }, 
          { path: 'vendor', loadChildren:() => import('./components/vendor/vendor.module').then(m=>m.VendorModule)},
          { path: 'main', component: MainComponent },
          {
             path: 'product', loadChildren: ()=> import('./components/product/product.module').then(m=>m.ProductModule)
          }, 
        ]},
    { path: "cart", component: CartComponent },
    { path:"payment", component: PaymentComponent },
    { path: "payment-confirm", component: PaymentConfirmComponent },
    { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)},
    
];

