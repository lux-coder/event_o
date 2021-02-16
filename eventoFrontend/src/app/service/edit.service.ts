import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ServerConstant } from '../constant/server-constant';

@Injectable({
  providedIn: 'root'
})
export class EditService {
  
  private _reqOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'text/plain;charset=utf-8') };
  private _reqJSONOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8') };

  private apiData = new BehaviorSubject<any[]>(null);
  public apiData$ = this.apiData.asObservable();
  
  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  constructor(private http: HttpClient) { }

  editRegion(value: any): Observable<any | HttpErrorResponse> {
    console.log("from editService, editRegion")
    let e = JSON.stringify(value);
    return this.http.post<any>(this.host + '/organizations/editRegion', e, this._reqJSONOptionsArgs);
  }  

  editCounty(element: any) : Observable<any | HttpErrorResponse> {
      console.log("from editService, editCounty")
      let e = JSON.stringify(element);
      return this.http.post<any>(this.host + '/organizations/editCounty', e, this._reqJSONOptionsArgs);
  }

  editCity(element: any): Observable<any | HttpErrorResponse> {
    console.log("from editService, editCity")
    console.log(element);
    let e = JSON.stringify(element);
    return this.http.post<any>(this.host + '/organizations/editCity', e, this._reqJSONOptionsArgs);
  }

  editEvent(element: any): Observable<any | HttpErrorResponse> {
    console.log("from editService, editEvent")
    console.log(element);
    let e = JSON.stringify(element);
    return this.http.post<any>(this.host + '/events/edit', e, this._reqJSONOptionsArgs);
  }
}
