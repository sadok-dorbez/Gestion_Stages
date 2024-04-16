import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CandidatDashboardComponent } from './components/candidat-dashboard/candidat-dashboard.component';
import {AddCandidatureComponent} from "./components/add-candidature/add-candidature.component";
import {UploadCvComponent} from "./components/upload-cv/upload-cv.component";
import {CalendarComponent} from "./components/calendar/calendar.component";
import {QuestionListComponent} from "./components/question-list/question-list.component";

const routes: Routes = [

  { path: "dashboard", component: CandidatDashboardComponent},
  { path: "postuler/:id", component: AddCandidatureComponent},
  { path: "uploadcv/:id", component: UploadCvComponent},
  {path: 'calendar', component: CalendarComponent, data: {title: 'Calendar'}},
  { path: 'question-list/:testId', component: QuestionListComponent ,data: { title: 'Question-list'}},


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CandidatRoutingModule { }
