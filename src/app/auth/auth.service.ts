import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { CoreHTTPService } from "../core/services/base";
import { tap } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class AuthService extends CoreHTTPService {
  constructor(protected override http: HttpClient, private router: Router) {
    super(http);
  }

  login(email: string, password: string) {
    return this.post("auth", { email, password }).pipe(
      tap((result) => {
        this.token = result.bearerToken;
        this.email = result.user.email;
        this.roleId = result.user.account.roles[0].id;
        this.roleType = result.user.account.roles[0].roleType;

      })
    );
  }

  registerCustomer(value: any) {
    return this.post("customer", value).pipe(
      tap((result) => {
        this.email = result.account.email;
      })
    );
  }

  registerVendor(value: any) {
    return this.post("vendor", value).pipe(
      tap((result) => {
        this.email = result.email;
        this.vendorId = result.id;
      })
    );
  }

  registerVendorAdmin(value: any) {
    return this.post("vendor/" + this.vendorId + "/vendor-admin", value).pipe(
      tap((result) => {
       
        this.email = result.user.email;
        this.vendorId = result.id;
      })
    );
  }

  set vendorId(value: any | null) {
    if (value === null) {
      value = "";
    }
    localStorage.setItem("vendorId", value);
  }

  get vendorId(): any | null {
    return localStorage.getItem("vendorId");
  }

  get isAuthorized(): boolean {
    if (this.roleId && this.email && this.token) {
      return true;
    }
    else return false;
  }
  set email(value: string) {
    if (value === null) { value = ''; }
    localStorage.setItem('email', value);
  }

  
  get email(): any | null {
    return localStorage.getItem('email');
  }

  set roleId(value: string | null) {
    if (value === null) {
      value = "";
    }
    localStorage.setItem("roleId", value);
  }

  get roleId(): string {
    return localStorage.getItem("roleId") as string;
  }

  set roleType(value: string | null) {
    if (value === null) {
      value = "";
    }
    localStorage.setItem("roleType", value);
  }

  get roleType(): string | null {
    return localStorage.getItem("roleType");
  }
  set token(value: string | null) {
    if (value === null) value = "";
    localStorage.setItem("token", value);
  }

  get token(): string | null {
    return localStorage.getItem("token");
  }

  logout() {
    this.token = null;
    localStorage.clear();
    this.router.navigate(["auth"]);
  }
}
