import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';;
import { AllMaterialModule } from 'src/app/shared/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { OrderHistoryComponent } from './order-history/order-history.component';
import { reportRoutes } from './report.routing';

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(reportRoutes),
        AllMaterialModule,
        FlexLayoutModule,
        ReactiveFormsModule,
    ],
    declarations: [
        OrderHistoryComponent
    ],
})

export class ReportModule { }
