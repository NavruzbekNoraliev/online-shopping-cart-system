import { Injectable } from '@angular/core';
import { CoreHTTPService } from './base';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VendorService extends CoreHTTPService {
  constructor(protected override http: HttpClient) {
    super(http)
   }

  list(): Observable<any> {
    return this.get('vendor/all');
  }
  create(vendor: any): Observable<any> {
    return this.post('vendor', vendor)
  }
  update(id: string, vendor: any): Observable<any> {
    return this.put('vendor', { id, vendor })
  }

  getAllPendingApprovalVendors(){
    return this.get('vendor/pending-approval', )
  }
  // deleteVendor(id: string): Observable <any> {
  //   return this.delete('vendor', id)
  // }

  createVendorAdmin(admin: any): Observable<any>{
    return this.post('vendor/vendor-admin', {admin})
  }
  updateVendorAdmin(id: string, vendor: any): Observable<any>{
    return this.put('vendor/vendor-admin', {id, vendor})
  }

  // deleteVendorAdmin(): Observable<any> {

  // }
  listVendorAdmins(): Observable<any>{
    return this.get('vendor/vendor-admin')
  }
}

/**
 * firstName
 * lastName
 * email
 * phone 
 * account {
 *  password
 * }
 */

/**
 * order_history [{
 *  date: purchase date
 *  products: [
 *    { 
 *    
 *    }
 *  ],
 * }]
 */

