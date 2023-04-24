import { Injectable } from "@angular/core";
import { CoreHTTPService } from "./base";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UserService extends CoreHTTPService {
    constructor(http: HttpClient) {
        super(http)
    }

    // USER MANAGEMENT
    // USER RELATED REQUESTS
    getOrderHistory(userId: string): Observable<any>{
        return this.get('transaction/user/'+userId+'/history')
    }
}
