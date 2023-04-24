import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { CategoryService } from 'src/app/core/services/category.service';
import { ProductService } from 'src/app/core/services/products.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {
  request$?: Observable<any>;
  getProduct$?: Observable<any>;
  form: FormGroup
  id!: string;
  categories!:any[]
  constructor(
    private route: ActivatedRoute,
    private productAPI: ProductService,
    private categoryAPI: CategoryService,
    private authAPI: AuthService,
    private router: Router,
    private fb: FormBuilder) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      vendorDTO: this.fb.group({
        id: ['', Validators.required],
      }),
      color: ['', Validators.required],
      categoryId: ['', Validators.required],
      available: [false, Validators.required],
      imageUrl: ['', Validators.required]
    })
    this.form.patchValue({vendorDTO: {id: this.authAPI.roleId}})
    this.route.params.subscribe((res:any) => {
      if(res.id) {
        console.log("update")
        this.id = res.id
        this.getProduct(res.id)
      }
    }).unsubscribe()
  }

  ngOnInit() {
    this.request$ =  this.categoryAPI.getAllCategories().pipe(
      tap(res => {
        this.categories = res;
      })
    )
  }

  getProduct(id:string) {
    this.getProduct$ = this.productAPI.getProductById(id).pipe(tap(res => {
      this.form.patchValue(res)
    }))
  }



  onSubmit(){
    if(this.id){ 
      this.request$ = this.productAPI.updateProductById(this.id, this.form.getRawValue()).pipe(
        tap(_ => {
          this.router.navigate(['/product/management/list']);
        })
      );
    } else {
      this.request$ = this.productAPI.addProductToStore(this.form.getRawValue()).pipe(
        tap(_ => {
          this.router.navigate(['/product/management/list']);
        })
      );
    }
   
    
  }

}
