import { Injectable } from "@angular/core";
import { CoreHTTPService } from "./base";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class TransactionService extends CoreHTTPService {
    constructor(http: HttpClient) {
        super(http)
    }
}
