import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductCardComponent } from './product-card/product-card.component';
import { AllMaterialModule } from 'src/app/shared/material.module';
import { StarRatingComponent } from './star-rating/star-rating.component';
import { ProductListComponent } from './product-list/product-list.component';
import { FlexLayoutModule } from '@angular/flex-layout';


@NgModule({
  imports: [
    CommonModule,
    FlexLayoutModule,
    AllMaterialModule
  ],
  declarations: [
    ProductCardComponent,
    StarRatingComponent,
    ProductListComponent
  ],
  exports: [
    ProductCardComponent,
    ProductListComponent
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
})
export class ProductPageModule { }
