import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OnetimePaymentComponent } from './onetime-payment/onetime-payment.component';
import { paymentRoutes } from './payment.routing';
import { PaymentComponent } from './payment.component';
import { PaymentConfirmComponent } from 'src/app/pages/payment-confirm/payment-confirm.component';
import { AllMaterialModule } from 'src/app/shared/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(paymentRoutes),
    AllMaterialModule,
    FlexLayoutModule,
    ReactiveFormsModule,
  ],
  declarations: [
    PaymentComponent,
    PaymentConfirmComponent,
    OnetimePaymentComponent,
  ],
})

export class PaymentModule {}
