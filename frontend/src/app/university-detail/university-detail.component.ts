import { Component } from '@angular/core';
import {UniversityDetail} from "../university";
import {UniversityService} from "../university.service";
import { ActivatedRoute } from "@angular/router";
import { Location } from "@angular/common";
import { MatDialog } from "@angular/material/dialog";
import {UniversityDetailCreateComponent} from "../university-detail-create/university-detail-create.component";
import {UniversityDetailDeleteComponent} from "../university-detail-delete/university-detail-delete.component";
import { filter, switchMap, tap } from "rxjs";

@Component({
  selector: 'app-university-detail',
  templateUrl: './university-detail.component.html',
  styleUrls: ['./university-detail.component.css']
})
export class UniversityDetailComponent {
  public readonly UniversityDetail = this.universityService.getUniversity(this.route.snapshot.queryParamMap.get('name')!);

  constructor(
      private route: ActivatedRoute,
      private universityService: UniversityService,

      private location: Location,
      public dialog: MatDialog,
  ) { }

  goBack(): void {
    this.location.back();
  }

  editUniversity(university: UniversityDetail): void {
    this.UniEditDialog(university);
  }

  private UniEditDialog(university: UniversityDetail): void {
    const oldUni = university;
    const dialogState = this.dialog.open(UniversityDetailCreateComponent, {
          data: {
            university,
            isEdit: true
          }
        }
    );

    dialogState.afterClosed().pipe(
        filter(Boolean),
        tap(() => this.goBack()),
        switchMap(newUni => this.universityService.updateUniversity(oldUni, newUni))
    ).subscribe();
  }

  deleteUniversity(university: UniversityDetail): void {
    this.uniDeleteDialog(university);
  }

  private uniDeleteDialog(university: UniversityDetail): void {
    const dialogRef = this.dialog.open(UniversityDetailDeleteComponent, {
          data: university
        }
    );

    dialogRef.afterClosed().pipe(
        filter(Boolean),
        tap(() => this.goBack()),
        switchMap(() => this.universityService.deleteUniversity(university.name))
    ).subscribe();
  }

}
