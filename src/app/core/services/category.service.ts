import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CoreHTTPService } from './base';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends CoreHTTPService{

  constructor(http: HttpClient) { super(http) }

  getAllCategories(): Observable<any>{
    return this.get('category')
  }
}
