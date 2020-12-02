import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Game } from '../game';
import { GameService } from '../game.service';

@Component({
  selector: 'app-game-detail',
  templateUrl: './game-detail.component.html',
  styleUrls: ['./game-detail.component.css']
})
export class GameDetailComponent implements OnInit {
  game: Game;

  constructor(private gameService: GameService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      if (window.history.state.game) { this.game = window.history.state.game; } 
      else {
        this.route.paramMap.subscribe(params => {
          const gameId = +params.get('id');
          this.getGameById(gameId);
        });
      }
    });
  }

  getGameById(gameId: number): void {
    this.gameService.getGameById(gameId)
      .subscribe(game => this.game = game);
  }

}
