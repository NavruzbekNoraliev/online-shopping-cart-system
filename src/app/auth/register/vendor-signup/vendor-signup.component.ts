import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-vendor-signup',
  templateUrl: './vendor-signup.component.html',
  styleUrls: ['./vendor-signup.component.css']
})
export class VendorSignupComponent implements OnInit {

  constructor(private route: Router) {

  }

  ngOnInit(): void {
      
  }

  navigateToLogin() {
    this.route.navigateByUrl("auth/login");
  }

}
