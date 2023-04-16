import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { routes } from './app.routing';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { ServiceComponent } from './service/service.component';

import { HttpClientModule } from "@angular/common/http";
import { MainComponent } from './components/main.component';
import { HeaderComponent } from './components/header/header.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { CartComponent } from './components/cart/cart.component';
import { FilterPipe } from './shared/filter.pipe';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    ServiceComponent,
    MainComponent,
    HeaderComponent,
    ProductListComponent,
    CartComponent,
    FilterPipe

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
