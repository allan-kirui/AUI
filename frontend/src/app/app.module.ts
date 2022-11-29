import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfessorsComponent } from './professors/professors.component';

import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // <-- NgModel lives here

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatTableComponent } from './mat-table/mat-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatListModule } from "@angular/material/list";
import { MatCardModule } from "@angular/material/card";
import { MatButtonModule } from "@angular/material/button";
import { MatDialogModule } from "@angular/material/dialog";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatRippleModule } from "@angular/material/core";
import { MatSelectModule } from "@angular/material/select";

import { ProfessorDetailComponent } from './professor-detail/professor-detail.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UniversitiesComponent } from './universities/universities.component';
import { HttpClientModule } from '@angular/common/http';
import { UniversityDetailComponent } from './university-detail/university-detail.component';
import { UniversityDetailCreateComponent } from './university-detail-create/university-detail-create.component';
import { UniversityDetailDeleteComponent } from './university-detail-delete/university-detail-delete.component';
import { ProfessorDetailCreateComponent } from './professor-detail-create/professor-detail-create.component';
import { ProfessorDetailDeleteComponent } from './professor-detail-delete/professor-detail-delete.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfessorsComponent,
    MatTableComponent,
    ProfessorDetailComponent,
    DashboardComponent,
    UniversitiesComponent,
    UniversityDetailComponent,
    UniversityDetailCreateComponent,
    UniversityDetailDeleteComponent,
    ProfessorDetailCreateComponent,
    ProfessorDetailDeleteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatCardModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    MatRippleModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    HttpClientModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
