import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorDetailDeleteComponent } from './professor-detail-delete.component';

describe('ProfessorDetailDeleteComponent', () => {
  let component: ProfessorDetailDeleteComponent;
  let fixture: ComponentFixture<ProfessorDetailDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorDetailDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessorDetailDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
