import { Component, OnInit } from '@angular/core';
import { Game } from '../game';
import { GameService } from '../game.service';
import { RatingsService } from '../ratings.service';

@Component({
  selector: 'app-most-recent',
  templateUrl: './most-recent.component.html',
  styleUrls: ['./most-recent.component.css']
})
export class MostRecentComponent implements OnInit {
  games: Game[];
  isLoading: boolean = true;;

  constructor(private gameService: GameService, private ratingsService: RatingsService) { }

  ngOnInit(): void {
    this.getMostRecentGames();
  }

  getMostRecentGames() {
    this.gameService.getMostRecentGames()
      .subscribe(games => {
        this.games = games;
        this.isLoading = false;
      });
  }

}
