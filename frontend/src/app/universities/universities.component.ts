import { Component } from '@angular/core';
import { filter, switchMap } from "rxjs";
import { MatDialog } from "@angular/material/dialog";

import {UniversityService} from "../university.service";
import {UniversityDetailCreateComponent} from "../university-detail-create/university-detail-create.component";
@Component({
  selector: 'app-universities',
  templateUrl: './universities.component.html',
  styleUrls: ['./universities.component.css']
})
export class UniversitiesComponent {
  constructor(
      public readonly universityService: UniversityService,
      public dialog: MatDialog
  ) { }

  createUniversity(): void {
    this.uniCreateDialog();
  }

  private uniCreateDialog(): void{
    const dialogRef = this.dialog.open(UniversityDetailCreateComponent);

    dialogRef.afterClosed().pipe(
        filter(Boolean),
        switchMap(university => this.universityService.createUniversity(university))
    ).subscribe();
  }

}
