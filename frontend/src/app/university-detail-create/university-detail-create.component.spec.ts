import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversityDetailCreateComponent } from './university-detail-create.component';

describe('UniversityDetailCreateComponent', () => {
  let component: UniversityDetailCreateComponent;
  let fixture: ComponentFixture<UniversityDetailCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniversityDetailCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UniversityDetailCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
