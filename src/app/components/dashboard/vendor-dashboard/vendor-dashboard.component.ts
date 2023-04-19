import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendor-dashboard',
  templateUrl: './vendor-dashboard.component.html',
  styleUrls: ['./vendor-dashboard.component.css']
})
export class VendorDashboardComponent implements OnInit {

  public paid: boolean = false;

  constructor(private route: Router) {}

  ngOnInit(): void {
      
  }

  changePaidToTrue() {
    this.paid = true;
  }

  navigateToLogIn() {
    this.route.navigateByUrl("/auth/login");
  }
}
