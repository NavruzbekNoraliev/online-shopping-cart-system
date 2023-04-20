import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CoreHTTPService } from './http.service';

export abstract class CoreService extends CoreHTTPService {

  constructor(protected endpoint: string, protected override http: HttpClient) {
    super(http);
  }

  list(page: number = 1, pageSize: number = 15, params?: any): Observable<any> {
    return this.get(`${this.endpoint}`, {
      page: `${page}`,
      size: `${pageSize}`,
      ...params
    });
  }

  retrieve(id: string): Observable<any> {
    const url = `${this.endpoint}/${id}`;
    return this.get(url);
  }

  create(model: any): Observable<any> {
    const url = `${this.endpoint}`;
    return this.post(url, model);
  }

  update(model: any): Observable<any> {
    console.log(model);
    const id = model instanceof FormData ? model.get('id') : model.id;
    const url = `${this.endpoint}/${id}`;
    return this.patch(url, model);
  }

  save(model: any) {
    if (model.id) {
      return this.update(model);
    } else {
      return this.create(model);
    }
  }

  search(search: string, page: number = 1, count: number = 15): Observable<any> {
    const url = `${this.endpoint}`;
    return this.get(url, {
      page: `${page}`,
      size: `${count}`,
      search,
    });
  }

  delete(id: string): Observable<any> {
    const url = `${this.endpoint}/${id}`;
    return this.core_delete(url);
  }

}
