import { Component, Input, OnInit } from '@angular/core';
import { Review } from '../review';
import { ReviewsService } from '../reviews.service';

@Component({
  selector: 'app-game-review',
  templateUrl: './game-review.component.html',
  styleUrls: ['./game-review.component.css']
})
export class GameReviewComponent implements OnInit {
  @Input() gameId: number;
  gameReview: Review;
  editorModeOn: boolean = true;

  constructor(private reviewsService: ReviewsService) { }

  ngOnInit(): void {
    this.getReview();
  }

  submitReview(review: string): void {
    const { gameReview } = this;
    if (!review) return;
    gameReview ? this.editReview(review) : this.addReview(review);
  }

  addReview(review: string) {
    const { gameId } = this;
    const newReview = { id: undefined, review, gameId };
    
    this.reviewsService.addReview(newReview)
      .subscribe(() => this.getReview());
  }

  editReview(review: string) {
    const { copyReview, gameId } = this;

    const editedReview = copyReview(this.gameReview);
    editedReview.review = review; 
    this.reviewsService.editReview(gameId, editedReview)
      .subscribe(() => this.getReview());
  }

  getReview() {
    const { gameId } = this;
    this.reviewsService.getReviewByGameId(gameId)
      .subscribe(gameReview => {
        gameReview ? this.toggleEditorModeOff() : this.toggleEditorModeOn();
        this.gameReview = gameReview;
      })
  }

  deleteReview() {
    const { gameId } = this;
    this.reviewsService.deleteReview(gameId)
      .subscribe(() => {
        this.gameReview = undefined;
        this.toggleEditorModeOn();
      })
  }

  copyReview(gameReview: Review): Review {
    const copy: Review = { ...gameReview };
    return copy;
  }

  toggleEditorModeOn(): void {
    this.editorModeOn = true;
  }

  toggleEditorModeOff(): void {
    this.editorModeOn = false;
  }

}
