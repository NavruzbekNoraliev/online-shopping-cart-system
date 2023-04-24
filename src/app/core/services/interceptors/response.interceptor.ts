import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpEvent, HttpHandler, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { tap } from 'rxjs/operators';
import { AuthService } from 'src/app/auth/auth.service';

@Injectable()
export class ResponseInterceptor implements HttpInterceptor {

  constructor(
    private authApi: AuthService,
    private snackbar: MatSnackBar) { }

  intercept(
  
    req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const modified = req.clone({ 
      setHeaders:this.addRequestHeaders() 
    });
    return next.handle(modified).pipe(tap(event => {

      if (event instanceof HttpResponse) {
        if (['DELETE', 'POST', 'PATCH', 'PUT'].includes(req.method)) {
          this.snackbar.open('Successfully executed your request.', 'Okay', {
            duration: 2500
          });
        }
      }
    }));

    
  }
  addRequestHeaders(){
    let headers:any = {}
    if (this.authApi.token) headers["Authorization"] = `Bearer ${this.authApi.token}`
    headers["Access-Control-Allow-Origin"] = "*"
    return headers
  }
}
