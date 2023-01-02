import { Injectable } from '@angular/core';
import {University, UniversityDetail} from "./university";
import { BehaviorSubject, catchError, map, Observable, of } from "rxjs";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class UniversityService {
  private static readonly universityUrl = '//localhost:8080/api/universities'; // URL to web api

  readonly universitiesBS = new BehaviorSubject<University[]>([]);

  constructor(private http: HttpClient) {
    this.getUniversities().subscribe(universities => {
      this.universitiesBS.next(universities);
    });
  }

  getUniversity(name: string): Observable<UniversityDetail> {
    return this.http.get<UniversityDetail>(UniversityService.universityUrl + '/' + name);
  }

  getUniversities(): Observable<University[]> {
    return this.http.get<{ universities: University[] }>(UniversityService.universityUrl).pipe(map(resp => resp.universities));
  }

  createUniversity(newUni: UniversityDetail): Observable<any> {
    this.universitiesBS.next(this.universitiesBS.value.concat(newUni.name));

    return this.http.post<UniversityDetail>(UniversityService.universityUrl, newUni).pipe(
        catchError(() => {
          this.universitiesBS.next(this.universitiesBS.value.filter(university => university !== newUni.name));
          return of(null);
        })
    );
  }



  updateUniversity(oldUni: UniversityDetail, newUni: UniversityDetail): Observable<any> {
    this.universitiesBS.next(this.universitiesBS.value
        .filter(university => oldUni.name !== university)
        .concat(newUni.name));

    return this.http.put<UniversityDetail>(UniversityService.universityUrl + '/' + oldUni.name, newUni).pipe(
        catchError(error => {
          console.log(error);

          this.universitiesBS.next(this.universitiesBS.value
              .filter(university => newUni.name !== university)
              .concat(newUni.name));

          this.universitiesBS.next(this.universitiesBS.value);
          return of(null); //case of an error in UI
        })
    );
  }

  deleteUniversity(name: string): Observable<any> {
    this.universitiesBS.next(this.universitiesBS.value.filter(university => university !== name));

    return this.http.delete<University>(UniversityService.universityUrl + '/' + name).pipe(
        catchError(error => {
          console.log(error);
          this.universitiesBS.next(this.universitiesBS.value.concat(name));
          return of(null);
        })
    );
  }


}
