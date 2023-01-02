import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of } from "rxjs";
import { HttpClient } from "@angular/common/http";

import {Professor} from "./professor";
import {ProfessorDetail} from "./professor";
import {University} from "./university";
import {UniversityService} from "./university.service";

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {
  private static readonly professorUrl = '//localhost:8080/api/professors';
  private static readonly universityUrl = '//localhost:8080/api/universities';

  readonly professorsBS = new BehaviorSubject<Map<string, Professor[]>>(new Map<string, Professor[]>());

  constructor(
      private http: HttpClient,
      private universityService: UniversityService
  ) {
    this.universityService.universitiesBS.subscribe(universities => {
      universities.forEach(university => {
        this.getProfessorsByUni(university).subscribe(professors => {
          this.professorsBS.value.set(university, professors);
        })
      })
    })

    this.professorsBS.next(this.professorsBS.value);
  }

  getProfessor(id: number): Observable<ProfessorDetail> {
    return this.http.get<ProfessorDetail>(ProfessorService.professorUrl + '/' + id);
  }

    getProfessors(): Observable<Professor[]> {
        return this.http.get<{ professors: Professor[] }>(ProfessorService.professorUrl).pipe(map(resp => resp.professors));
    }

  getProfessorsByUni(name:University) {
    return this.http.get<{professors: Professor[] }>(ProfessorService.universityUrl + '/' + name + '/professors').pipe(map(resp => resp.professors));
  }

  createProfessor(newProf: ProfessorDetail): Observable<any> {
    const newProfs = this.professorsBS.value.get(newProf.university)!.concat({ id: newProf.id, name: newProf.name });
    this.professorsBS.value.set(newProf.university, newProfs);
    this.professorsBS.next(this.professorsBS.value);

    return this.http.post<ProfessorDetail>(ProfessorService.professorUrl, newProf).pipe(
        catchError(err => {
          console.log(err);

          const oldProfs = this.professorsBS.value.get(newProf.university)!.filter(professor => professor.id !== newProf.id);
          this.professorsBS.value.set(newProf.university, oldProfs);
          this.professorsBS.next(this.professorsBS.value);

          return of(null);
        })
    );
  }

  updateProfessor(oldProf: ProfessorDetail, newProf: ProfessorDetail): Observable<any> {
    const newProfs = this.professorsBS.value.get(newProf.university)!
        .filter(professor => professor.id !== oldProf.id)
        .concat({ id: newProf.id, name: newProf.name });
    this.professorsBS.value.set(newProf.university, newProfs);
    this.professorsBS.next(this.professorsBS.value);

    return this.http.put<ProfessorDetail>(ProfessorService.professorUrl + '/' + oldProf.id, newProf).pipe(
        catchError(error => {
          console.log(error);

          const oldProfs = this.professorsBS.value.get(newProf.university)!
              .filter(professor => professor.id !== newProf.id)
              .concat({ id: oldProf.id, name: oldProf.name });
          this.professorsBS.value.set(newProf.university, oldProfs);
          this.professorsBS.next(this.professorsBS.value);

          return of(null);
        })
    );
  }

    deleteProfessor(professor: ProfessorDetail): Observable<any> {
        const newProfs = this.professorsBS.value.get(professor.university)!
            .filter(professor => professor.id !== professor.id);
        this.professorsBS.value.set(professor.university, newProfs);
        this.professorsBS.next(this.professorsBS.value);

        return this.http.delete<Professor>(ProfessorService.professorUrl + '/' + professor.id).pipe(
            catchError(err => {
                console.log(err);

                const oldProfs = this.professorsBS.value.get(professor.university)!.concat(professor);
                this.professorsBS.value.set(professor.university, oldProfs);
                this.professorsBS.next(this.professorsBS.value);

                return of(null);
            })
        );
    }

}

