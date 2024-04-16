import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CandidatRoutingModule } from './candidat-routing.module';
import { CandidatDashboardComponent } from './components/candidat-dashboard/candidat-dashboard.component';
import { AddCandidatureComponent } from './components/add-candidature/add-candidature.component';
import {FormsModule} from "@angular/forms";
import { UploadCvComponent } from './components/upload-cv/upload-cv.component';
import { SujetDetailsComponent } from './components/sujet-details/sujet-details.component';
import {CalendarComponent} from "./components/calendar/calendar.component";
import {QuestionListComponent} from "./components/question-list/question-list.component";
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';


@NgModule({
  declarations: [
    CandidatDashboardComponent,
    AddCandidatureComponent,
    UploadCvComponent,
    SujetDetailsComponent,
    CalendarComponent,
    QuestionListComponent
  ],
  imports: [
    CommonModule,
    CandidatRoutingModule,
    FormsModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    })
    
    
  ]
})
export class CandidatModule { }
