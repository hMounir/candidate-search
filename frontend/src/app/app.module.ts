import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {TableModule} from 'primeng/table';
import {ToolbarModule} from 'primeng/toolbar';
import {CandidateService} from './CandidateService';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DialogModule} from 'primeng/dialog';
import {MessageService, MultiSelectModule, SplitButtonModule} from 'primeng/primeng';
import {ReactiveFormsModule} from '@angular/forms';
import {SpinnerModule} from 'primeng/spinner';
import {InputTextModule} from 'primeng/inputtext';
import {MessagesModule} from 'primeng/messages';
import {PanelModule} from 'primeng/panel';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    TableModule,
    ToolbarModule,
    HttpClientModule,
    DialogModule,
    SplitButtonModule,
    ReactiveFormsModule,
    SpinnerModule,
    MultiSelectModule,
    InputTextModule,
    MessagesModule,
    PanelModule
  ],
  providers: [CandidateService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
