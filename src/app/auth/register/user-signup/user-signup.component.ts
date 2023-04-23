import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { AuthService } from "../../auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: "app-user-signup",
  templateUrl: "./user-signup.component.html",
  styleUrls: ["./user-signup.component.scss"],
})
export class UserSignupComponent implements OnInit {
  request$?: Observable<any>;
  private requesting: BehaviorSubject<any> = new BehaviorSubject(false);
  requesting$: Observable<boolean> = this.requesting.asObservable();

  form: FormGroup;

  // "username": "arda@gmail.com",
  // "password": "Aa@12345"
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authAPI: AuthService
  ) {
    this.form = this.fb.group({
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      phone: [null, Validators.required],
      account: this.fb.group({
        email: [null, Validators.required],
        password: [null, Validators.required],
      }),
      shippingAddress: this.fb.group({
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
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    const values = this.form.getRawValue();
    this.requesting.next(true);
    this.request$ = this.authAPI.registerCustomer(values).pipe(
      tap((_) => {
        console.log("registered successfully");
        this.requesting.next(false);
        this.router.navigate(["auth/login"]);
      })
    );
  }
}
