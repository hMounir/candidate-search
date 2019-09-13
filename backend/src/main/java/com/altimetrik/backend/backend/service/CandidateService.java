package com.altimetrik.backend.backend.service;

import com.altimetrik.backend.backend.model.Candidate;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private List<Candidate> candidateList;

    public CandidateService() {
        this.candidateList = new ArrayList<>();
    }

    public void add(Candidate candidate) throws Exception{
        if(StringUtils.trimToNull(candidate.getCandidate()) == null
                || StringUtils.trimToNull(candidate.getProject()) == null
                || !Pattern.matches("^20((1[1-9])|([2-9][0-9]))$",String.valueOf(candidate.getYear()))
                || ArrayUtils.isEmpty(candidate.getSkills())){
            throw new Exception("error in inputs");
        }
        candidateList.add(candidate);
    }

    public List<Candidate> getCandidateList(){
        return candidateList;
    }

    public List<Candidate> searchCandidates(String[] skills) throws Exception {
        if(skills.length > 3){
            throw new Exception("more than 3 skills");
        }

        return candidateList.stream().filter(candidate -> containsSkill(candidate.getSkills(),skills))
                .sorted((o1, o2) -> Integer.valueOf(o2.getYear()).compareTo(o1.getYear())).collect(Collectors.toList());
    }

    private boolean containsSkill(String[] candSkill,String[] filteredSkills){
        for(String skill:candSkill){
            if(Arrays.asList(filteredSkills).contains(skill)){
                return true;
            }
        }
        return false;
    }
}
