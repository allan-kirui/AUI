import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { filter, switchMap, tap } from "rxjs";
import { MatDialog } from "@angular/material/dialog";

import { Professor, ProfessorDetail} from "../professor";
import { ProfessorService } from '../professor.service';
import {ProfessorDetailCreateComponent} from "../professor-detail-create/professor-detail-create.component";
import {ProfessorDetailDeleteComponent} from "../professor-detail-delete/professor-detail-delete.component";
import { UniversityService } from '../university.service';

@Component({
  selector: 'app-professor-detail',
  templateUrl: './professor-detail.component.html',
  styleUrls: ['./professor-detail.component.css']
})


export class ProfessorDetailComponent {
  constructor(
      private route: ActivatedRoute, //holds information about the route to this instance of the ProfessorDetailComponent
      private professorService: ProfessorService,
      private universityService: UniversityService,
      private location: Location,
      public dialog: MatDialog,
  ) {}

  public readonly professorDetailBs = this.professorService.getProfessor(parseInt(this.route.snapshot.paramMap.get('id')!));
  public readonly universityBs = this.universityService.universitiesBS;

  editProfessor(professor: ProfessorDetail) {
    this.profEditDialog(professor);
  }

  deleteProfessor(professor: ProfessorDetail) {
    this.profDeleteDialog(professor);
  }

  private profEditDialog(professor: ProfessorDetail): void{
    const oldProf = professor;
    const dialogRef = this.dialog.open(ProfessorDetailCreateComponent,{
      data: {
        professor,
        isEdit: true,
        universities: this.universityService.universitiesBS.value
      }
    });

    dialogRef.afterClosed().pipe(
        filter(Boolean),
        tap(() => this.goBack()),
        switchMap(newProf => this.professorService.updateProfessor(oldProf, newProf))
    ).subscribe();
  }

  ngOnInit(): void {

  }

  private profDeleteDialog(professor: ProfessorDetail): void {
    const dialogRef = this.dialog.open(ProfessorDetailDeleteComponent, {
          data: professor
        }
    );

    dialogRef.afterClosed().pipe(
        filter(Boolean),
        tap(() => this.goBack()),
        switchMap(() => this.professorService.deleteProfessor(professor))
    ).subscribe();
  }

  goBack(): void {
    this.location.back();
  }
}
