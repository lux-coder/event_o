import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { ServerConstant } from '../constant/server-constant';
import { City } from '../model/city';
import { Events } from '../model/events';
import { Region } from '../model/region';

@Injectable({
  providedIn: 'root'
})
export class UnitsService {

  private apiData = new BehaviorSubject<any[]>(null);
  public apiData$ = this.apiData.asObservable();

  private _reqOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'text/plain;charset=utf-8') };
  private _reqJSONOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8') };

  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  regions: Observable<Region[]>;

  constructor(private http: HttpClient) {
   }

  getRegions(): Observable<any | HttpErrorResponse> {
    console.log("from unitsService, getRegions");    
    return this.http.get<any>(this.host + '/organizations/getRegions', this._reqOptionsArgs);
  }

  getAllCounties(): Observable<any | HttpErrorResponse> {
    console.log("from unitsService, getAllCounties");        
    return this.http.get<any>(this.host + '/organizations/getAllCounties', this._reqOptionsArgs);
  }

  getCounties(selectedRegions): Observable<any | HttpErrorResponse> {
    console.log("from unitsService, getCounties onChange");    
    return this.http.post<any>(this.host + '/organizations/getCounties', selectedRegions, this._reqOptionsArgs);
  }

  getAllCities(): Observable<any | HttpErrorResponse> {
    console.log("from unitsService, getAllCities");
    return this.http.get<any>(this.host + '/organizations/getAllCities', this._reqOptionsArgs);
  }

  getCities(selectedCounties): Observable<any | HttpErrorResponse> {
    console.log("from unitsService, getCities onChange");    
    return this.http.post<any>(this.host + '/organizations/getCities', selectedCounties, this._reqOptionsArgs);
  }

  searchEventName(name: string): Observable<Event[]> {
    if(!name) {
      return of([]);
    }
    return this.http.get<Event[]>(this.host + '/events/search/' + name, this._reqOptionsArgs);
  }

  searchEvents(event: Events): Observable<any | HttpErrorResponse> {
    console.log("from unitsService, searchEvents")
    let e = JSON.stringify(event);
    return this.http.post<any>(this.host + '/events/search/event', e, this._reqJSONOptionsArgs);
  }  
  

  setData(data) { 
    console.log("settingData service");    
    this.apiData.next(data);
  }
}
