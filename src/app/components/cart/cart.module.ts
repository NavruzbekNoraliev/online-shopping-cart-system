import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartComponent } from './cart.component';
import { cartRoutes } from './cart.routing';
import { RouterModule } from '@angular/router';
import { AllMaterialModule } from 'src/app/shared/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';


@NgModule({
  imports: [
    CommonModule,
    AllMaterialModule,
    FlexLayoutModule,
    RouterModule.forChild(cartRoutes)
  ],
  declarations: [CartComponent]
})
export class CartModule { }
