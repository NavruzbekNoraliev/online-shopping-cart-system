import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import { ProductPageModule } from '../pages/product/product-page.module';
import { AllMaterialModule } from '../shared/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
@NgModule({
  imports: [
    CommonModule,
    ProductPageModule,
    AllMaterialModule,
    FlexLayoutModule
  ],
  declarations: [
    MainComponent,
    PageNotFoundComponent
  ],
})
export class MainModule { }
