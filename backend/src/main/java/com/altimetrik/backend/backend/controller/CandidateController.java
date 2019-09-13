package com.altimetrik.backend.backend.controller;

import com.altimetrik.backend.backend.model.Candidate;
import com.altimetrik.backend.backend.model.Response;
import com.altimetrik.backend.backend.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates/v1")
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @PostMapping("/")
    public HttpEntity<Response> addCandidate(@RequestBody Candidate candidate){
        try {
            candidateService.add(candidate);
            return ResponseEntity.status(201).body(new Response("success",201));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(400).body(new Response("error in inputs",400));
        }
    }

    @GetMapping("/{skills}")
    public ResponseEntity<?> searchCandidates(@PathVariable String[] skills){
        try {
            return ResponseEntity.status(201).body(candidateService.searchCandidates(skills));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping("/")
    public List<Candidate> getAll(){
        return candidateService.getCandidateList();
    }
}
