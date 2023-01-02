import { Component, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup} from "@angular/forms";

import {UniversityDetail} from "../university";
@Component({
  selector: 'app-university-detail-delete',
  templateUrl: './university-detail-delete.component.html',
  styleUrls: ['./university-detail-delete.component.css']
})
export class UniversityDetailDeleteComponent {
  constructor(
      public dialogRef: MatDialogRef<UniversityDetailDeleteComponent>,
      @Inject(MAT_DIALOG_DATA) public university: UniversityDetail
  ) { }

  delete() {
    this.dialogRef.close(true);
  }

  cancel() {
    this.dialogRef.close(false);
  }
}
