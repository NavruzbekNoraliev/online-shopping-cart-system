import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginComponent implements OnInit {

  request$?: Observable<any>;
  private requesting: BehaviorSubject<any> = new BehaviorSubject(false);
  requesting$: Observable<boolean> = this.requesting.asObservable();

  form: FormGroup;

  // "username": "arda@gmail.com",
  // "password": "Aa@12345"
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authAPI: AuthService
  ) {
    this.form = this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    const values = this.form.value;
    this.requesting.next(true);
    this.request$ = this.authAPI.login(values.username, values.password).pipe(
      tap(_ => {
        this.requesting.next(false);
        this.router.navigate(['/main']);
      }, _ => {
        this.requesting.next(false);
      })
    );
  }
}
