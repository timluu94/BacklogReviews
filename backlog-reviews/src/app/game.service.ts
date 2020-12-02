import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';

import { Observable } from 'rxjs';
import { Game } from './game';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private url = "http://localhost:8080";

  constructor(private http: HttpClient, private router: Router) { }

  searchGames(term: string, page: number): Observable<Game[]> {
    const params = new HttpParams()
      .set('term', term)
      .set('page', `${page}`);
    this.router.navigate(['/search'], { queryParams: { term, page } });
    
    return this.http.get<Game[]>(`${this.url}/api/search`, { params });
  }

  getGameById(gameId: number): Observable<Game> {
    return this.http.get<Game>(`${this.url}/api/game/${gameId}`);
  }

  getMostRecentGames(): Observable<Game[]> {
    return this.http.get<Game[]>(`${this.url}/api/most_recent`);
  }


}
