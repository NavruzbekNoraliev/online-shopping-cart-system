import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {

  constructor(private route: Router) {

  }

  ngOnInit(): void {
      
  }

  navigateToLogin() {
    this.route.navigateByUrl("auth/login");
  }

}
