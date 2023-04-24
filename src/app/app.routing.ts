import { Routes } from '@angular/router';
import { MainComponent } from './components/main.component';
import { FullComponent } from './components/layouts/full/full.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

export const routes: Routes = [
    {
        path: '',
        component: FullComponent,
        // canActivate, put authguard for security to dashboard
        children: [ 
          { path: '',     component: MainComponent },
          { path: 'vendor',  loadChildren: () => import('./components/vendor/vendor.module').then(m=>m.VendorModule)},
          { path: 'product', loadChildren: () => import('./components/product/product.module').then(m=>m.ProductModule)}, 
          { path: 'payment', loadChildren: () => import('./components/payment/payment.module').then(m => m.PaymentModule)},
          { path: "cart",    loadChildren: () => import('./components/cart/cart.module').then(m=>m.CartModule)},
          { path: "report",    loadChildren: () => import('./components/report/report.module').then(m=>m.ReportModule)},
        ]},
        { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)},
        { path: '404', component: PageNotFoundComponent},
        // { path: '**', redirectTo: '/404'}
        // { path: "payment-confirm", component: PaymentConfirmComponent },
    
];

