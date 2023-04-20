import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { VendorSignupComponent } from './register/vendor-signup/vendor-signup.component';
import { UserSignupComponent } from './register/user-signup/user-signup.component';
import { RouterModule } from '@angular/router';
import { authRoutes } from './auth.routing';
import { AllMaterialModule } from '../shared/material.module';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(authRoutes),
    ReactiveFormsModule,
    AllMaterialModule
  ],
  declarations: [
    LoginComponent,
    VendorSignupComponent,
    UserSignupComponent
  ]
})
export class AuthModule { }
