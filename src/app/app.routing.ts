import { Routes } from '@angular/router';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { CartComponent } from './components/cart/cart.component';
import { MainComponent } from './components/main.component';

export const routes: Routes = [
    { path: '', component: MainComponent },
    { path: "product-list", component: ProductListComponent },
    { path: "cart", component: CartComponent },
    { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)}
];

