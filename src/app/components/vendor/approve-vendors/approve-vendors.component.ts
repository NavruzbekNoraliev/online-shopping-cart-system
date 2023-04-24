import { Component, OnInit } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { VendorService } from 'src/app/core/services/vendor.service';

@Component({
  selector: 'app-approve-vendors',
  templateUrl: './approve-vendors.component.html',
  styleUrls: ['./approve-vendors.component.scss']
})
export class ApproveVendorsComponent implements OnInit {
  request$?: Observable<any>;
  displayedColumns: string[] = ['name', 'phone', 'status', 'email', 'address', 'approve'];
  vendorsToApprove:any = []
  constructor(private vendorAPI: VendorService) {
    this.getAll()
  }

  ngOnInit() {
  }

  getAll(){
    this.request$ = this.vendorAPI.getAllPendingApprovalVendors().pipe(tap(res => {
      this.vendorsToApprove = res
    }))
  }

  approve(id: string){
    this.request$ = this.vendorAPI.approveVendor(id).pipe(tap(_ => {
      this.vendorsToApprove = this.vendorsToApprove.filter((v:any) => v.id != id);
    }))
  }

}
