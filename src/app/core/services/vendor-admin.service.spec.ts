/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { VendorAdminService } from './vendor-admin.service';

describe('Service: VendorAdmin', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VendorAdminService]
    });
  });

  it('should ...', inject([VendorAdminService], (service: VendorAdminService) => {
    expect(service).toBeTruthy();
  }));
});
