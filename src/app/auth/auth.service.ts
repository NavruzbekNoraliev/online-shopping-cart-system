import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CoreHTTPService } from '../core/services/base';
import { tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthService extends CoreHTTPService {

    constructor(
        protected override http: HttpClient,
        private router: Router,
    ) {
        super(http);
    }

    login(username: string, password: string) {
        return this.post('auth', { username, password }).pipe(tap(result => {
            this.token = result.bearerToken;
            this.username = result.user;
            this.role = result.user.account.roles[0];
        }));
    }
    registerCustomer(username: string, password: string){
        return this.post('customer', { username, password }).pipe(tap(result => {
            this.token = result.token;
            this.username = result.user;
        }));
    }

    set username(value: any | null) {
        if (value === null) { value = ''; }
        localStorage.setItem('username', `${value.first_name} ${value.last_name}`);
    }

    get username(): any | null {
        return localStorage.getItem('username');
    }

    set role(value: string | null) {
        if (value === null) { value = ''; }
        localStorage.setItem('role', value);
    }

    get role(): string | null {
        return localStorage.getItem('role');
    }

    set token(value: string | null) {
        if (value === null) value = '';
        localStorage.setItem('token', value);
    }

    get token(): string | null {
        return localStorage.getItem('token');
    }
    logout() {
        this.token = null;
        localStorage.clear();
        this.router.navigate(['auth']);
    }

}
