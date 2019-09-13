package com.altimetrik.backend.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    private String candidate;
    private String project;
    private int year;
    private String[] skills;

    public Candidate(String candidate) {
        this.candidate = candidate;
    }
}
