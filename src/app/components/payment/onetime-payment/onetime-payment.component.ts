import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { PaymentService } from 'src/app/core/services/payment.service';

@Component({
  selector: 'app-onetime-payment',
  templateUrl: './onetime-payment.component.html',
  styleUrls: ['./onetime-payment.component.scss']
})
export class OnetimePaymentComponent implements OnInit {
  request$?: Observable<any>;
  form: FormGroup;
  constructor(
    private snackbar: MatSnackBar,
    private authAPI: AuthService,
    private router: Router,
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
    console.log("One time payment")
  }

  makeOneTimePayment(){
    const cardDetails = this.form.controls['cardDetails'].getRawValue();
    const body ={ 
      cardDetails: {
        cardNumber: cardDetails.cardNumber,
        nameOnCard: cardDetails.nameOnCard,
        cvv: parseInt(cardDetails.cvv) ,
        expMonth: cardDetails.expDate.split('/')[0],
        expYear:cardDetails.expDate.split('/')[1]
      },
      transactionAmount: 0,
      vendorId: parseInt(this.authAPI.roleId)

    }
    this.request$ = this.paymentAPI.processOneTimePayment(body).pipe(tap(res => {
        if(res.body.transactionStatus == "TS" && res.body.message == "OK"){
          this.router.navigate(['/'])
        } else {
          this.snackbar.open(res.body.message, 'Okay', {
            duration: 2500
          });
        }
    }))
  }


}
