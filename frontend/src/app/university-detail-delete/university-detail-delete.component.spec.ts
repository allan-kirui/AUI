import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversityDetailDeleteComponent } from './university-detail-delete.component';

describe('UniversityDetailDeleteComponent', () => {
  let component: UniversityDetailDeleteComponent;
  let fixture: ComponentFixture<UniversityDetailDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniversityDetailDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UniversityDetailDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
