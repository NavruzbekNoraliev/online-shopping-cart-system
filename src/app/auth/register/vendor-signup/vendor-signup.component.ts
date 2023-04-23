import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { AuthService } from "../../auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: "app-vendor-signup",
  templateUrl: "./vendor-signup.component.html",
  styleUrls: ["./vendor-signup.component.scss"],
})
export class VendorSignupComponent implements OnInit {
  request$?: Observable<any>;
  private requesting: BehaviorSubject<any> = new BehaviorSubject(false);
  requesting$: Observable<boolean> = this.requesting.asObservable();

  formGroup1: FormGroup;
  formGroup2: FormGroup;

  // "username": "arda@gmail.com",
  // "password": "Aa@12345"
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authAPI: AuthService
  ) {
    this.formGroup1 = this.fb.group({
      name: [null, Validators.required],
      phone: [null, Validators.required],
      email: [null, Validators.required],
      address: this.fb.group({
        street: [null, Validators.required],
        city: [null, Validators.required],
        state: [null, Validators.required],
        zip: [null, Validators.required],
      }),
      billingAddress: this.fb.group({
        street: [null, Validators.required],
        city: [null, Validators.required],
        state: [null, Validators.required],
        zip: [null, Validators.required],
      }),
      accountDetails: this.fb.group({
        bankName: [null, Validators.required],
        accountNumber: [null, Validators.required],
        routingNumber: [null, Validators.required],
      }),
    });
    this.formGroup2 = this.fb.group({
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      vendorAdminPhone: [null, Validators.required],
      account: this.fb.group({
        email: [null, Validators.required],
        password: [null, Validators.required],
      }),
    });
  }

  ngOnInit(): void {}

  onSubmitVendor() {
    const vendorValues = this.formGroup1.getRawValue();
    this.requesting.next(true);
    this.request$ = this.authAPI.registerVendor(vendorValues).pipe(
      tap((_) => {
        this.requesting.next(false);
        // this.router.navigate(["auth/login"]);
      })
    );
  }

  onSubmitVendorAdmin() {
    const vendorAdminValues = this.formGroup2.getRawValue();
    this.requesting.next(true);
    this.request$ = this.authAPI.registerVendorAdmin(vendorAdminValues).pipe(
      tap((_) => {
        this.requesting.next(false);
        this.router.navigate(["auth/login"]);
      })
    );

  }

}
