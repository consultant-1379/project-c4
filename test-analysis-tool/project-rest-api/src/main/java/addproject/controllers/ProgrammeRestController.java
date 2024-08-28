package addproject.controllers;

import addproject.pojo.Programme;
import addproject.services.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProgrammeRestController {

    @Autowired
    private ProgrammeService service;

    @PostMapping("/add-project")
    public ResponseEntity<Programme> addEnmProject(@RequestBody Programme programme) {
        service.insertEnmProject(programme);

        return ResponseEntity.ok().body(programme);
    }

    @GetMapping(path = "/get-all-projects", produces="application/json")
    public ResponseEntity<List<Programme>> getAllEnmProjects() {
        List<Programme> listOfProgrammes= service.getAllENMProjects();

        return ResponseEntity.ok().body(listOfProgrammes);
    }
}