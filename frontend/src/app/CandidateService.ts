import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Candidate } from './Candidate';

@Injectable()
export class CandidateService {

  constructor(private httpClient: HttpClient) {}

  getCandidates() {
    return this.httpClient.get<Candidate[]>('http://localhost:8080/candidates/v1/');
  }

  addCandidate(candidate: Candidate) {
    return this.httpClient.post('http://localhost:8080/candidates/v1/', candidate);
  }

  searchCandidate(skills: string[]) {
    console.log(skills.join(','));
    return this.httpClient.get<Candidate[]>('http://localhost:8080/candidates/v1/' + skills.join(','));
  }
}
