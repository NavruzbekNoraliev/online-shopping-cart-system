import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import { HeaderComponent } from './header/header.component';
import { ProductListComponent } from './product/product-list/product-list.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [MainComponent, HeaderComponent, ProductListComponent]
})
export class MainModule { }
