import { Component, Inject, OnInit} from '@angular/core';

import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup} from "@angular/forms";

import {UniversityDetail} from "../university";

@Component({
  selector: 'app-university-detail-create',
  templateUrl: './university-detail-create.component.html',
  styleUrls: ['./university-detail-create.component.css']
})
export class UniversityDetailCreateComponent implements OnInit{
  constructor(
      private dialogRef: MatDialogRef<UniversityDetailCreateComponent>,
      private formbuilder: FormBuilder,
      @Inject(MAT_DIALOG_DATA) public data?: {
        isEdit: true,
        university: UniversityDetail
      }
  ) { }

    public form: FormGroup = this.formbuilder.group({
        name: this.data?.university ? this.data.university.name : '',
        location: this.data?.university ? this.data.university.location : ''
    });

    ngOnInit() {
        if (this.data?.isEdit) {
            this.form.get('name')?.disable();
        }
    }
    
    create() {
        this.form.get('name')?.enable();
        this.dialogRef.close(this.form.value);
    }
    
    cancel() {
        this.dialogRef.close();
    }

   
}
