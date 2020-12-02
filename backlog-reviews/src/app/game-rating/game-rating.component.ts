import { Component, Input, OnInit } from '@angular/core';
import { Rating } from '../rating';
import { RatingsService } from '../ratings.service'

@Component({
  selector: 'app-game-rating',
  templateUrl: './game-rating.component.html',
  styleUrls: ['./game-rating.component.css']
})
export class GameRatingComponent implements OnInit {
  @Input() gameId: number;
  gameRating: Rating;
  isChanging: boolean = false;
  
  constructor(private ratingsService: RatingsService) { }

  ngOnInit(): void {
    this.getRating();
  }

  getRating() {
    const { gameId, ratingsService } = this;
    ratingsService.getRatingByGameId(gameId).subscribe(gameRating => {
      this.gameRating = gameRating;
      this.isChanging = false;
    });
  }

  addRating(rating: number) {
    const { gameId, ratingsService } = this;

    const newRating: Rating = { id: undefined, rating, gameId };
    ratingsService.addRating(newRating)
      .subscribe(() => this.getRating())
  }

  editRating(rating: number) {
    const { copyRating, gameId, ratingsService } = this;

    const editedRating = copyRating(this.gameRating);
    editedRating.rating = rating;
    ratingsService.editRating(gameId, editedRating)
      .subscribe(() => this.getRating());
  }

  copyRating(gameRating: Rating): Rating {
    const copy: Rating = { ...gameRating };
    return copy;
  }

  changeRating() {
    this.isChanging = true;
  }

  cancelChange() {
    this.isChanging = false;
  }

  submitRating(rating: number) {
    !this.gameRating ? this.addRating(rating) : this.editRating(rating);
  }
}
