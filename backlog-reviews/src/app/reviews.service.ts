import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Review } from './review';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewsService {
  private url = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getReviewByGameId(gameId: number): Observable<Review> {
    const { http, url } = this;
    return http.get<Review>(`${url}/api/reviews/${gameId}`);
  }

  addReview(gameReview: Review): Observable<any> {
    const { http, url } = this;
    return http.post<any>(`${url}/api/reviews`, gameReview);
  }

  editReview(gameId: number, gameReview: Review) {
    const { http, url } = this;
    return http.put<any>(`${url}/api/reviews/${gameId}`, gameReview);
  }

  deleteReview(gameId: number) {
    const { http, url } = this;
    return http.delete(`${url}/api/reviews/${gameId}`);
  }

}
