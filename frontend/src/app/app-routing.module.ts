import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorsComponent } from './professors/professors.component';
import { UniversitiesComponent } from './universities/universities.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfessorDetailComponent } from './professor-detail/professor-detail.component'
import { UniversityDetailComponent } from './university-detail/university-detail.component'

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'professors', component: ProfessorsComponent },
  { path: 'professors/:id', component: ProfessorDetailComponent },
  { path: 'universities', component: UniversitiesComponent },
  { path: 'university', component: UniversityDetailComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
