import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { routes } from './app.routing';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { FilterPipe } from './shared/filter.pipe';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MainModule } from './components/main.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FullComponent } from './components/layouts/full/full.component';
import { SidebarComponent } from './components/layouts/full/sidebar/sidebar.component';
import { AllMaterialModule } from './shared/material.module';
import { MenuItems } from './shared/menu-items';
import { ResponseInterceptor } from './core/services/interceptors/response.interceptor';
import { HeaderComponent } from './components/layouts/full/header/header.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { OnetimePaymentComponent } from './components/payment/onetime-payment/onetime-payment.component';
import { NgxSandCreditCardsComponent, NgxSandCreditCardsModule } from 'ngx-sand-credit-cards';


@NgModule({
  declarations: [
    AppComponent,
    FilterPipe,
    FullComponent,
    SidebarComponent,
    OnetimePaymentComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    HttpClientModule,
    MainModule,
    NgxSandCreditCardsModule,
    AllMaterialModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [ 
    MenuItems,
    { provide: HTTP_INTERCEPTORS, useClass: ResponseInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
