import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversityDetailComponent } from './university-detail.component';

describe('UniversityDetailComponent', () => {
  let component: UniversityDetailComponent;
  let fixture: ComponentFixture<UniversityDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniversityDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UniversityDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
