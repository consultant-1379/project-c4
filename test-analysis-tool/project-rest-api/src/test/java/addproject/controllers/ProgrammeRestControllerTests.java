package addproject.controllers;


import addproject.pojo.Programme;
import addproject.services.ProgrammeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProgrammeRestControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private ProgrammeService mockProgrammeService;

    @InjectMocks
    private ProgrammeRestController programmeRestController;

    @Test
    void addEnmProject_projectAdded() {
        Programme p = new Programme();
        ResponseEntity responseEntity = restTemplate.postForEntity("/api/add-project", p, Programme.class);

        Programme responseBody = (Programme) responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAllEnmProject_emptyList() {
        List<Programme> emptList = new ArrayList<>();

        when(mockProgrammeService.getAllENMProjects()).thenReturn(emptList);

        ResponseEntity<List<Programme>> responseEntity = programmeRestController.getAllEnmProjects();

        List<Programme> responseBody = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseBody.size());
    }

}
