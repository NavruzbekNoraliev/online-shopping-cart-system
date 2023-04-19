import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { routes } from './app.routing';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';

import { HttpClientModule } from "@angular/common/http";
import { CartComponent } from './components/cart/cart.component';
import { FilterPipe } from './shared/filter.pipe';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { PaymentComponent } from './components/payment/payment.component';
import { HeaderComponent } from './components/header/header.component';
import { NgxSandCreditCardsModule } from 'ngx-sand-credit-cards';
import { PaymentConfirmComponent } from './pages/payment-confirm/payment-confirm.component';
import { VendorDashboardComponent } from './components/dashboard/vendor-dashboard/vendor-dashboard.component';


import { MainModule } from './components/main.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CartComponent,
    FilterPipe,
    PaymentComponent,
    PaymentConfirmComponent,
    VendorDashboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MainModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule
    NgxSandCreditCardsModule
    
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
