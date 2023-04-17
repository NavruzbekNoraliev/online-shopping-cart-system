import { Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { MainComponent } from './components/main.component';

export const routes: Routes = [
    { path: '', component: MainComponent },
    { path: "cart", component: CartComponent },
    { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)},
    { path: 'product', loadChildren: ()=> import('./components/product/product.module').then(m=>m.ProductModule)}
];

