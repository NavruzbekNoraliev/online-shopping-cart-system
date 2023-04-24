import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CoreHTTPService } from './base';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProductService extends CoreHTTPService{

  constructor(http: HttpClient) { super(http) }

  addProductToStore(body: any): Observable<any>{
    return this.post('product/add', body)
  }
  updateProductById(id: string, body:any) {
    return this.put('product/update/'+ id, body)
  }
  getAllProductsByVendor(vendorId: string):  Observable<any> {
    return this.get('product/vendor/'+  vendorId)
  }
  getProductById(id:string):Observable<any>{
    return this.get('product/'+id);
  }
  getAllPendingApprovalProducts(){
    return this.get('product/all?available=false')
  }
  approveProductAvailability(id: string) {
    return this.put('product/updateAvailable/'+id, { available: true})
  }
  getAllProducts():Observable<any>{
    return this.get('product/all')
  }
}
