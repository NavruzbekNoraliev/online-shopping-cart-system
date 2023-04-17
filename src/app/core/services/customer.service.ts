import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
 BASE_API: string ="api/v1/customer"
constructor() { }

  // Post
  /**
   * firstName
   * lastName
   * email
   * phone
   * account {
   *    password
   *    List<Role> => [ADMIN, ...]
   * }
   * // optional 
   * shippingAddress
   * billingAddress
   * */ 

}
