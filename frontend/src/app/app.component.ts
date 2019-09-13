import {Component, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';
import {CandidateService} from './CandidateService';
import {Candidate} from './Candidate';
import {FormControl, FormGroup} from '@angular/forms';
import {MessageService, SelectItem} from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  candidatesList: Candidate[];
  display: boolean;
  skillsList: SelectItem[];

  newCandidate = new Candidate();

  skills: string[];

  newCandidateForm = new FormGroup({
    candidate: new FormControl(this.newCandidate.candidate),
    project: new FormControl(this.newCandidate.project),
    year: new FormControl(this.newCandidate.year),
    skills: new FormControl(this.newCandidate.skills),
  });

  searchSkillsForm = new FormGroup({
    skills: new FormControl(this.skills),
  });

  constructor(private candidateService: CandidateService, private messageService: MessageService) {
    this.skillsList = [
      {label: 'Java', value: 'Java'},
      {label: 'C++', value: 'C++'},
      {label: 'Hibernate', value: 'Hibernate'},
      {label: 'Angular', value: 'Angular'},
      {label: 'JavaScript', value: 'JavaScript'}
    ];
  }


  ngOnInit(): void {
    this.candidateService.getCandidates().subscribe(value => this.candidatesList = value);
  }

  showDialog() {
    this.display = true;
    this.newCandidate = new Candidate();
    this.newCandidateForm = new FormGroup({
      candidate: new FormControl(this.newCandidate.candidate),
      project: new FormControl(this.newCandidate.project),
      year: new FormControl(this.newCandidate.year),
      skills: new FormControl(this.newCandidate.skills),
    });

  }

  submit() {
    this.candidateService.addCandidate(this.newCandidateForm.value)
      .subscribe(
        res => {
          this.messageService.add({severity: 'success', summary: 'Candidate Added Successfully', detail: 'Candidate Added Successfully'});
          this.display = false;
          this.candidatesList.push(this.newCandidateForm.value);
        },
        err => {
          console.log(err);
          this.messageService.add({severity: 'error', summary: 'Error in adding candidate', detail: 'Error in adding candidate'});
          this.display = false;
        },
        () => console.log('HTTP request completed.')
      );
    // console.log(this.newCandidateForm.value);
  }

  cancel() {
    this.display = false;
  }

  searchSkills() {
    this.candidateService.searchCandidate(this.searchSkillsForm.value.skills)
      .subscribe(
        res => {
          console.log(res);
          this.messageService.add({severity: 'success', summary: 'search returned', detail: 'search returned'});
          this.candidatesList = res;
        },
        err => {
          console.log(err);
          this.messageService.add({severity: 'error', summary: 'Error in searching', detail: 'Error in searching'});
        },
        () => console.log('HTTP request completed.')
      );
  }
}
