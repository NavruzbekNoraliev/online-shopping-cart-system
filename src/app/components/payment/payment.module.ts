import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OnetimePaymentComponent } from './onetime-payment/onetime-payment.component';
import { paymentRoutes } from './payment.routing';
import { PaymentComponent } from './payment.component';
import { PaymentConfirmComponent } from 'src/app/pages/payment-confirm/payment-confirm.component';
import { NgxSandCreditCardsModule } from 'ngx-sand-credit-cards';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(paymentRoutes),
    NgxSandCreditCardsModule,
  ],
  declarations: [
    OnetimePaymentComponent,
    // PaymentComponent,
    PaymentConfirmComponent,
  ],
})

export class PaymentModule {}
