import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import { HeaderComponent } from './layouts/full/header/header.component';
import { ProductPageModule } from '../pages/product/product-page.module';
import { AllMaterialModule } from '../shared/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxSandCreditCardsModule } from 'ngx-sand-credit-cards/public-api'

@NgModule({
  imports: [
    CommonModule,
    ProductPageModule,
    FlexLayoutModule,
    AllMaterialModule,
    NgxSandCreditCardsModule
  ],
  declarations: [
    HeaderComponent,
    MainComponent
  ],
  exports:[
    HeaderComponent
  ]
})
export class MainModule { }
