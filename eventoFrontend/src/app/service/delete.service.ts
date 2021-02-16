import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ServerConstant } from '../constant/server-constant';

@Injectable({
  providedIn: 'root'
})
export class DeleteService { 

  private _reqOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'text/plain;charset=utf-8') };
  private _reqJSONOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8') };

  private apiData = new BehaviorSubject<any[]>(null);
  public apiData$ = this.apiData.asObservable();
  
  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  constructor(private http: HttpClient) { }

  deleteEvent(value: any): Observable<any | HttpErrorResponse> {
    console.log("from deleteService, deleteEvent");
    let e = JSON.stringify(value);
    console.log(e);
    return this.http.post<any>(this.host + '/events/delete', e, this._reqJSONOptionsArgs);
  }  

  deleteCity(element: any): Observable<any | HttpErrorResponse>  {
    console.log("from deleteService, deleteCity");
    let e = JSON.stringify(element);
    return this.http.post<any>(this.host + '/organizations/deleteCity', e, this._reqJSONOptionsArgs);
  }

  deleteCounty(element: any): Observable<any | HttpErrorResponse>  {
    console.log("from deleteService, deleteCounty");
    let e = JSON.stringify(element);
    return this.http.post<any>(this.host + '/organizations/deleteCounty', e, this._reqJSONOptionsArgs);
  }

  deleteRegion(element: any): Observable<any | HttpErrorResponse> {
    console.log("from deleteService, deleteRegion");
    let e = JSON.stringify(element);
    console.log(e);
    return this.http.post<any>(this.host + '/organizations/deleteCounty', e, this._reqJSONOptionsArgs);
  }  
  
}
