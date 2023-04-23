import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductCardComponent } from './product-card/product-card.component';
import { AllMaterialModule } from 'src/app/shared/material.module';
import { StarRatingComponent } from './star-rating/star-rating.component';


@NgModule({
  imports: [
    CommonModule,
   
    AllMaterialModule
  ],
  declarations: [
    ProductCardComponent,
    StarRatingComponent
  ],
  exports: [
    ProductCardComponent
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
})
export class ProductPageModule { }
