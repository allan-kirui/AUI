import { Component } from '@angular/core';
import {Professor} from '../professor';
import {University} from '../university';
import {ProfessorService} from '../professor.service';
import {UniversityService} from '../university.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  professors: Professor[] = [];
  universities: University[] = [];

  constructor(private professorService: ProfessorService, private universityService: UniversityService) { }

  ngOnInit(): void {
    this.getProfessors();
    this.getUniversities();
  }

  getProfessors(): void {
    this.professorService.getProfessors()
        .subscribe(professors => this.professors = professors.slice(1, 5));
  }

  getUniversities(): void {
    this.universityService.getUniversities()
        .subscribe(universities => this.universities = universities.slice(1, 5));
  }
}
