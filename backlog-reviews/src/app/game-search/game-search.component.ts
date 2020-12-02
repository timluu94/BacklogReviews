import { Component, OnInit } from '@angular/core';
import { Game } from '../game';
import { GameService } from '../game.service';

@Component({
  selector: 'app-game-search',
  templateUrl: './game-search.component.html',
  styleUrls: ['./game-search.component.css']
})
export class GameSearchComponent implements OnInit {
  games: Game[];
  
  canGoToNext: boolean;
  page: number;
  term: string;

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
  }

  search(term: string, page: number): void {
    this.games = null;
    this.term = term;

    this.gameService.searchGames(term, page)
      .subscribe(games => {
        this.games = games.slice(0, 10);
        this.canGoToNext = games.length > 10 ? true : false;
        this.page = page;
      });
  }

  goToPrevPage() {
    const { page, term } = this;
    if (page > 1) {
      this.search(term, page-1);
    }
  }

  goToNextPage(): void {
    const { canGoToNext, page, term } = this;
    if (canGoToNext) {
      this.search(term, page+1);
    }
  }

  shouldDisplayPagination(): boolean {
    const { games } = this;
    if (!games) return false;
    return games.length > 0 ? true : false;
  }
}
