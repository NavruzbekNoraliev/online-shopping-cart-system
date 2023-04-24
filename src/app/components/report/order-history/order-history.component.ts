import { Component, OnInit } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.scss']
})
export class OrderHistoryComponent implements OnInit{
  constructor(
    private authAPI: AuthService,
    private userAPI: UserService){

  }
  request$?: Observable<any>;
  orders:any[]=[]
  panelOpenState = false;
  displayedColumns: string[] = ['name', 'descriptions', 'quantity', 'category', 'vendorName', 'pricePerUnit', 'total']
  ngOnInit(): void {
    this.getOrderHistory();
  }

  getOrderHistory(){
    this.request$ = this.userAPI.getOrderHistory(this.authAPI.id).pipe(tap(res=>{
       this.orders = res
    }))
  }
}
