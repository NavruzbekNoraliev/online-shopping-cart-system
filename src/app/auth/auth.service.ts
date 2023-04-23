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
        this.username = result.user;
        this.role = result.user.account.roles[0];
      })
    );
  }

  registerCustomer(value: any) {
    return this.post("customer", value).pipe(
      tap((result) => {
        this.username = result.account.email;
      })
    );
  }

  registerVendor(value: any) {
    return this.post("vendor", value).pipe(
      tap((result) => {
        this.username = result.email;
        this.vendorId = result.id;
      })
    );
  }

  registerVendorAdmin(value: any) {
    return this.post("vendor/" + this.vendorId + "/vendor-admin", value).pipe(
      tap((result) => {
        this.username = result.email;
        this.vendorId = result.id;
      })
    );
  }

  set vendorId (value: any | null) {
    if(value === null) {
      value = "";
    }
    localStorage.setItem("vendorId", value);
  }

  get vendorId (): any | null {
    return localStorage.getItem("vendorId");
  }

    get isAuthorized(): boolean{
        if(this.role && this.username && this.token) {
            return true;
        }
        else return false;
    }
    set username(value: any | null) {
        if (value === null) { value = ''; }
        localStorage.setItem('username', `${value.first_name} ${value.last_name}`);
    }

    get roleType(): any| null {
        return localStorage.getItem('roleType')
    }
    set roleType(value: any | null) {
        if (value === null) { value = ''; }
        localStorage.setItem('roleType', value);
    }

    get username(): any | null {
        return localStorage.getItem('username');
    }

  set role(value: string | null) {
    if (value === null) {
      value = "";
    }
    localStorage.setItem("role", value);
  }

  get role(): string | null {
    return localStorage.getItem("role");
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
