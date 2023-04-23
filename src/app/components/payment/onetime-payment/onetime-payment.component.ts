import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PaymentService } from 'src/app/core/services/payment.service';

@Component({
  selector: 'app-onetime-payment',
  templateUrl: './onetime-payment.component.html',
  styleUrls: ['./onetime-payment.component.scss']
})
export class OnetimePaymentComponent implements OnInit {
  
  form: FormGroup;
  constructor(
    private paymentAPI: PaymentService,
    private fb: FormBuilder) {
    this.form = this.fb.group({
      cardDetails: this.fb.group({
        cardNumber: [null, Validators.required],
        nameOnCard: [null, Validators.required],
        cvv: [null, Validators.required],
        expDate: [null, Validators.required],
      }),
      amount: [20000, Validators.required],
      billingAddress: this.fb.group({
        street: [null, Validators.required],
        city: [null, Validators.required],
        state: [null, Validators.required],
        zipCode: [null, Validators.required]
      })
    });
   }

  ngOnInit() {
  }

  makeOneTimePayment(){
    
  }


}
