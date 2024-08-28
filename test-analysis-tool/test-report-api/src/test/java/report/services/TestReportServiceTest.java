package report.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import report.pojo.HighestTestFail;
import report.pojo.TestReportSummary;
import report.repositories.JenkinsRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class TestReportServiceTest {

    @Autowired
    TestReportService testReportService;

    @MockBean
    JenkinsRepository jenkinsRepository;

    @Test
    void getAllTestsForCNA() {
        TestReportSummary summary = new TestReportSummary("CNA", 5, 2, 3, 0, 500L, new HighestTestFail());
        String jenkinsUrl = "jenkinsService.com";
        String jobName = "myTestJob";
        when(jenkinsRepository.getAllTestReports(jenkinsUrl, jobName)).thenReturn(summary);
        int actualTests = 5;
        int actualPasses = 2;
        int actualFails = 3;
        int actualSkips = 0;

        assertEquals(actualTests, testReportService.getAllReportsForCNA(jenkinsUrl, jobName).getTotalTests());
        assertEquals(actualPasses, testReportService.getAllReportsForCNA(jenkinsUrl, jobName).getTotalPasses());
        assertEquals(actualFails, testReportService.getAllReportsForCNA(jenkinsUrl, jobName).getTotalFails());
        assertEquals(actualSkips, testReportService.getAllReportsForCNA(jenkinsUrl, jobName).getTotalSkips());
    }

    @Test
    void getAllTestsForCNANull() {
        TestReportSummary summary = new TestReportSummary();
        String jobName = "myTestJob";
        summary.setReportName(jobName);
        String jenkinsUrl = "jenkinsService.com";
        when(jenkinsRepository.getAllTestReports(jenkinsUrl, jobName)).thenReturn(summary);
        int actualTests = 0;
        int actualPasses = 0;
        int actualFails = 0;
        int actualSkips = 0;

        assertEquals(actualTests, testReportService.getAllReportsForCNA(jenkinsUrl, jobName).getTotalTests());
        assertEquals(actualPasses, testReportService.getAllReportsForCNA(jenkinsUrl, jobName).getTotalPasses());
        assertEquals(actualFails, testReportService.getAllReportsForCNA(jenkinsUrl, jobName).getTotalFails());
        assertEquals(actualSkips, testReportService.getAllReportsForCNA(jenkinsUrl, jobName).getTotalSkips());
        assertEquals(jobName, summary.getReportName());
    }

    @Test
    void getAllReportsTestNull() {
        TestReportSummary actual = new TestReportSummary();
        String jenkinsJobUrl = "http://localhost:8000";
        List<String> jobNames = Arrays.asList("TestProject", "AnotherTestProject");
        when(jenkinsRepository.getAllTestReports(jenkinsJobUrl, "TestProject")).thenReturn(actual);
        when(jenkinsRepository.getAllTestReports(jenkinsJobUrl, "AnotherTestProject")).thenReturn(actual);
        TestReportSummary expected = testReportService.getAllReportsForProject(jenkinsJobUrl, jobNames);

        assertEquals(actual.getTotalTests(), expected.getTotalTests());
        assertEquals(actual.getTotalPasses(), expected.getTotalPasses());
        assertEquals(actual.getTotalFails(), expected.getTotalFails());
        assertEquals(actual.getTotalSkips(), expected.getTotalSkips());
        assertEquals(actual.getHighestTestFail().getFails(), expected.getHighestTestFail().getFails());
        assertEquals(actual.getHighestTestFail().getTestName(), expected.getHighestTestFail().getTestName());
    }
}
