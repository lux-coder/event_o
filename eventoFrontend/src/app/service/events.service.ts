import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject, Observable, ReplaySubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { ServerConstant } from '../constant/server-constant';
import { Events } from '../model/events';

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  private apiData = new BehaviorSubject<Events[]>(null);
  public apiData$ = this.apiData.asObservable();

  private _reqOptionsArgs = { headers: new HttpHeaders().set('Content-Type', 'application/json') };

  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  events: Observable<Events[]>;

  constructor(private http: HttpClient) {
   }

  getEvents(): Observable<any | HttpErrorResponse> {
    console.log("from events service");    
    return this.http.get<any>(this.host + '/events/getEvents', this._reqOptionsArgs);
  }

}
