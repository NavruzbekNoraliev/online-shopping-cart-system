import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable, tap } from "rxjs";
import { CoreHTTPService } from "./base/http.service";

@Injectable({
  providedIn: "root",
})
export class ProductService extends CoreHTTPService {
  constructor(http: HttpClient, private router: Router) {
    super(http);
  }

  getAllProducts(): Observable<any> {
    return this.get("product/all");
  }
}
