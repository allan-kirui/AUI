import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorDetailCreateComponent } from './professor-detail-create.component';

describe('ProfessorDetailCreateComponent', () => {
  let component: ProfessorDetailCreateComponent;
  let fixture: ComponentFixture<ProfessorDetailCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorDetailCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessorDetailCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
