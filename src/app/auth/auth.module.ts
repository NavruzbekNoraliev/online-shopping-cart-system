import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { VendorSignupComponent } from './register/vendor-signup/vendor-signup.component';
import { UserSignupComponent } from './register/user-signup/user-signup.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    LoginComponent,
    VendorSignupComponent,
    UserSignupComponent
  ]
})
export class AuthModule { }
