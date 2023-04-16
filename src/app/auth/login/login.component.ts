import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email = "";
  password = "";
  error = "";
  loading = false;

  constructor(private route: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.loading = true;
    this.error = "";
    if(!this.email || !this.password) {
      this.error = "Please enter your email and password!";
    } else {

    }
  }

  navigateToHome() {
    // this.route.navigate(["customer-register"]);
    this.route.navigateByUrl("/");
  }

  navigateToCustomerSignIn() {
    // this.route.navigate(["customer-register"]);
    this.route.navigateByUrl("auth/customer-register");
  }

  navigateToVendorSignIn() {
    // this.route.navigate(["customer-register"]);
    this.route.navigateByUrl("auth/vendor-register");
  }

}
