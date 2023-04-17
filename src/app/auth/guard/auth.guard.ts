import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  constructor(private _route: Router) {}
  canActivate(route: ActivatedRouteSnapshot): any {
      return true;
   
    // this._route.navigate(['/login']);
    // return false;
  }
}
