import { Routes } from '@angular/router';
import { MainComponent } from './components/main.component';

export const routes: Routes = [
    { path: '', component: MainComponent },
    { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)}
];

