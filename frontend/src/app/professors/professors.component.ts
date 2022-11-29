import { Component } from '@angular/core';
import {Professor} from '../professor'
import {ProfessorService} from '../professor.service';

@Component({
  selector: 'app-professors',
  templateUrl: './professors.component.html',
  styleUrls: ['./professors.component.css']
})
export class ProfessorsComponent{


  professors:Professor[] = [];

  constructor(private professorService: ProfessorService) {
  }

  getProfessors(): void {
    this.professorService.getProfessors().subscribe(professors => this.professors = professors);
  }

  ngOnInit(): void {
    this.getProfessors();
  }

}
