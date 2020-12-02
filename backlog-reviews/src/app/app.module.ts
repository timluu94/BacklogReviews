import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { GameDetailComponent } from './game-detail/game-detail.component';
import { GameSearchComponent } from './game-search/game-search.component';
import { GameRatingComponent } from './game-rating/game-rating.component';
import { GameReviewComponent } from './game-review/game-review.component';
import { MostRecentComponent } from './most-recent/most-recent.component';

@NgModule({
  declarations: [
    AppComponent,
    GameDetailComponent,
    GameSearchComponent,
    GameRatingComponent,
    GameReviewComponent,
    MostRecentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }