import { Observable } from "rxjs";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { environment } from "../../../env/env.dev";

export abstract class CoreHTTPService {
  protected get baseURL() {
    return environment.baseURL + "/";
  }

  constructor(protected http: HttpClient) {}

  protected get(
    relativeURL: string,
    params?:
      | HttpParams
      | {
          [param: string]: string | string[];
        }
  ): Observable<any> {
    return this.http.get(this.baseURL + relativeURL, { params });
  }

  protected put(relativeURL: string, body: any) {
    return this.http.put(this.baseURL + relativeURL, body);
  }

  protected patch(relativeURL: string, body: any) {
    return this.http.patch(this.baseURL + relativeURL + "/", body);
  }

  protected post(
    relativeURL: string,
    body: any,
    headers?: HttpHeaders
  ): Observable<any> {
    return this.http.post(this.baseURL + relativeURL, body, { headers });
  }

  protected core_delete(relativeURL: string) {
    return this.http.delete(this.baseURL + relativeURL + "/");
  }

  protected postFile(relativeURL: string, body: any) {
    return this.http.post(this.baseURL + relativeURL + "/", body, {
      reportProgress: true,
      observe: "events",
    });
  }
}
