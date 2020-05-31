import { TestBed } from '@angular/core/testing';

import { CustomDatePickerI18nService } from './custom-date-picker-i18n.service';

describe('CustomDatePickerI18nService', () => {
  let service: CustomDatePickerI18nService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomDatePickerI18nService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
