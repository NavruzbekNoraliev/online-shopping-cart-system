import { Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { MainComponent } from './components/main.component';
// import { PaymentConfirmComponent } from './pages/payment-confirm/payment-confirm.component';
import { FullComponent } from './components/layouts/full/full.component';
// import { AuthGuard } from './auth/guard/auth.guard';

export const routes: Routes = [
    {
        path: '',
        component: FullComponent,
        // canActivate, put authguard for security to dashboard
        children: [{ 
            path: '', 
            redirectTo: '/main', 
            pathMatch: 'full' 
          }, 
          { path: 'main',     component: MainComponent },
          { path: 'vendor',  loadChildren: () => import('./components/vendor/vendor.module').then(m=>m.VendorModule)},
          { path: 'product', loadChildren: () => import('./components/product/product.module').then(m=>m.ProductModule)}, 
          { path: 'payment', loadChildren: () => import('./components/payment/payment.module').then(m => m.PaymentModule)},
          { path: "cart",    loadChildren: () => import('./components/cart/cart.module').then(m=>m.CartModule)},
        ]},
    { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)},
    // { path: "payment-confirm", component: PaymentConfirmComponent },
    
];

