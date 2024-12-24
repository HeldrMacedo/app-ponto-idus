import { Injectable } from '@angular/core';
import { TimeClockRecord } from '../models/time-clock-record';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TimeClockRecordService {

  API = 'http://localhost:8080/time';

  constructor(private http: HttpClient) { }

  register(timeRegister: TimeClockRecord): Observable<TimeClockRecord>  {
    return this.http.post<TimeClockRecord>(this.API + '/register', timeRegister);
  }

  listTimeClockRecord(userId: number): Observable<TimeClockRecord[]>  {
    return this.http.get<TimeClockRecord[]>(this.API + '/list/' + userId);
  }
}
