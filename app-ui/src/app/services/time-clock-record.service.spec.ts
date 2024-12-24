import { TestBed } from '@angular/core/testing';

import { TimeClockRecordService } from './time-clock-record.service';

describe('TimeClockRecordService', () => {
  let service: TimeClockRecordService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TimeClockRecordService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
