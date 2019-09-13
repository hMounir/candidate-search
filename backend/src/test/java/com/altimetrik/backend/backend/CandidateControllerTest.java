package com.altimetrik.backend.backend;

import com.altimetrik.backend.backend.controller.CandidateController;
import com.altimetrik.backend.backend.model.Candidate;
import com.altimetrik.backend.backend.service.CandidateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc
public class CandidateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGetAllCandidates_thenReturnJsonArray() throws Exception {
        mvc.perform(get("/candidates/v1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].candidate", is(candidate.getCandidate())));
    }

    @Test
    public void givenNewCandidateWhenAddingCandidate_thenStatus() throws Exception {
        mvc.perform(post("/candidates/v1/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"candidate\": \"Test\",\n" +
                        "  \"project\": \"Test\",\n" +
                        "  \"skills\": [\n" +
                        "    \"Java\"\n" +
                        "  ],\n" +
                        "  \"year\": 2019\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));
    }

    @Test
    public void givenNewCandidateWithEmptyCandidateWhenAddingCandidate_thenStatus() throws Exception {
        mvc.perform(post("/candidates/v1/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void givenNewCandidateWithInvalidDataWhenAddingCandidate_thenStatus() throws Exception {
        mvc.perform(post("/candidates/v1/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"candidate\": \"\",\n" +
                        "  \"project\": \"\",\n" +
                        "  \"skills\": [\n" +
                        "    \"Java\"\n" +
                        "  ],\n" +
                        "  \"year\": 2019\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void givenSkillsWhenSearchingForCandidates() throws Exception {
        mvc.perform(get("/candidates/v1/")
                .pathInfo("")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"candidate\": \"Test\",\n" +
                        "  \"project\": \"Test\",\n" +
                        "  \"skills\": [\n" +
                        "    \"Java\"\n" +
                        "  ],\n" +
                        "  \"year\": 2019\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void givenNoSkillsWhenSearchingForCandidates() throws Exception {
        mvc.perform(get("/candidates/v1/")
                .pathInfo("")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"candidate\": \"Test\",\n" +
                        "  \"project\": \"Test\",\n" +
                        "  \"skills\": [\n" +
                        "    \"Oracle\"\n" +
                        "  ],\n" +
                        "  \"year\": 2019400\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}
