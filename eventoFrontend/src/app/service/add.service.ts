import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Observable } from 'rxjs';
import { ServerConstant } from '../constant/server-constant';
import { Events } from '../model/events';
import { map, switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AddService {
  

  private _reqOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'text/plain;charset=utf-8') };
  private _reqJSONOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8') };

  private apiData = new BehaviorSubject<any[]>(null);
  public apiData$ = this.apiData.asObservable();
  
  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  constructor(private http: HttpClient) { }

  getRegions(): Observable<any | HttpErrorResponse> {
    console.log("from addService, getRegions");    
    return this.http.get<any>(this.host + '/organizations/getAllRegions', this._reqOptionsArgs);
  }

  getCounties(): Observable<any | HttpErrorResponse> {
    console.log("from addService, getCounties");    
    return this.http.get<any>(this.host + '/organizations/getCountiesFull', this._reqOptionsArgs);
  }

  getCities(): Observable<any | HttpErrorResponse> {
    console.log("from addService, getCities");    
    return this.http.get<any>(this.host + '/organizations/getAllCities', this._reqOptionsArgs);
  }

  getEvents(): Observable<any | HttpErrorResponse> {
    console.log("from addService, getEvents");    
    return this.http.get<any>(this.host + '/events/getEvents', this._reqOptionsArgs);
  }

  addEvent(any): void {
    console.log("from addService, addEvent")
    console.log(any);
    let e = JSON.stringify(any);
    console.log(e);
    this.http.post<any>(this.host + '/events/save', e, this._reqJSONOptionsArgs);
    console.log("SENT");
  }  

  addCity(value: any): Observable<any | HttpErrorResponse> {
    console.log("from addService, addCity")    
    let e = JSON.stringify(value);
    return this.http.post<any>(this.host + '/organizations/city/save', e, this._reqJSONOptionsArgs );    
  }


  setData(data) { 
    console.log("settingData service");    
    this.apiData.next(data);
  }

}
