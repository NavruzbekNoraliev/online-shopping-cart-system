import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CoreHTTPService } from './base';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService extends CoreHTTPService{

  constructor(http: HttpClient) { super(http) }

  processOneTimePayment(body: any): Observable<any>{
    return this.http.post('payment', body)
  }
}
