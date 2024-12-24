import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimeClockRecordComponent } from './time-clock-record.component';

describe('TimeClockRecordComponent', () => {
  let component: TimeClockRecordComponent;
  let fixture: ComponentFixture<TimeClockRecordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TimeClockRecordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TimeClockRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
