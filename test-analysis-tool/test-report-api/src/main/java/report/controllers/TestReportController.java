package report.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report.pojo.TestReportSummary;
import report.services.TestReportService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/testReport")
public class TestReportController {

    @Autowired
    TestReportService testReportService;

    @GetMapping(value = "/getAllReportsForCNA/{jobName}", produces = {"application/json"})
    public ResponseEntity<TestReportSummary> getAllTestsForCNA(@RequestParam String jenkinsUrl, @PathVariable String jobName) {
        TestReportSummary testReportSummary = testReportService.getAllReportsForCNA(jenkinsUrl, jobName);
        return ResponseEntity.ok().body(testReportSummary);
    }

    @PostMapping(value = "/getAllReportsForProject/{productName}")
    public ResponseEntity<TestReportSummary> getAllTestsForProject(@PathVariable String productName, @RequestParam String jenkinsUrl, @RequestBody List<String> jobNames) {
        TestReportSummary testReportSummary = testReportService.getAllReportsForProject(jenkinsUrl, jobNames);
        testReportSummary.setReportName(productName);
        return ResponseEntity.ok().body(testReportSummary);
    }
}
