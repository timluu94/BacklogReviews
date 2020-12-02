import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rating } from './rating';

@Injectable({
  providedIn: 'root'
})
export class RatingsService {
  private url = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getRatingByGameId(gameId: number): Observable<Rating> {
    const { http, url } = this;
    return http.get<Rating>(`${url}/api/ratings/${gameId}`);
  }

  addRating(gameRating: Rating): Observable<any> {
    const { http, url } = this;
    return http.post<any>(`${url}/api/ratings`, gameRating);
  }

  editRating(gameId: number, gameRating: Rating): Observable<any> {
    const { http, url } = this;
    return http.put<any>(`${url}/api/ratings/${gameId}`, gameRating);
  }

  deleteRating(gameId: number): Observable<any> {
    const { http, url } = this;
    return http.delete<any>(`${url}/api/ratings/${gameId}`);
  }

}
