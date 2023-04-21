import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import { ProductPageModule } from '../pages/product/product-page.module';
import { AllMaterialModule } from '../shared/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  imports: [
    CommonModule,
    ProductPageModule,
    FlexLayoutModule,
    AllMaterialModule,
  ],
  declarations: [
    MainComponent
  ],
})
export class MainModule { }
