import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ApproveVendorsComponent } from './approve-vendors/approve-vendors.component';
import { ListVendorsComponent } from './list-vendors/list-vendors.component';
import { AllMaterialModule } from 'src/app/shared/material.module';
import { vendorRoutes } from './vendor.routing';
import { RouterModule } from '@angular/router';
@NgModule({
  imports: [
    CommonModule,
    FlexLayoutModule,
    AllMaterialModule,
    RouterModule.forChild(vendorRoutes)
  ],
  declarations: [
    ApproveVendorsComponent,
    ListVendorsComponent
  ],
})
export class VendorModule {

 }
