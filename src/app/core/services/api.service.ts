import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getProduct() {
    return this.http.get<any>("http://localhost:8001/api/v1/product/all")
    .pipe(map((res:any) => {
      return res;
    }));
  }

}
