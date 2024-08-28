package report.repositories;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import report.pojo.HighestTestFail;
import report.pojo.TestReportSummary;
import report.services.JenkinsService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class JenkinsRepositoryTest {

    @Mock
    JenkinsService mockJenkinsService;
    @Mock
    JenkinsServer mockServer;
    @InjectMocks
    JenkinsRepository jenkinsRepository = new JenkinsRepositoryImpl();

    @SneakyThrows
    @Test
    void getZeroJobsBackTest() {
        Job job = mock(Job.class);
        JobWithDetails jobDetails = mock(JobWithDetails.class);
        BuildWithDetails buildDetails = mock(BuildWithDetails.class);
        List<Build> buildList = new ArrayList<>();
        Map<String, Job> jobs = new HashMap<>();
        jobs.put("myJob", job);


        when(mockJenkinsService.getInstance("http://localhost:8080")).thenReturn(mockServer);
        when(mockServer.getJobs()).thenReturn(jobs);
        when(jobDetails.getAllBuilds()).thenReturn(buildList);
        when(job.details()).thenReturn(jobDetails);

        Map<String, HighestTestFail> map = new HashMap<>();
        HighestTestFail highestTestFail = new HighestTestFail();
        when(mockJenkinsService.getMaxFailingTest(map)).thenReturn(highestTestFail);
        TestReportSummary summary = jenkinsRepository.getAllTestReports("http://localhost:8080", "myJob");
        when(mockJenkinsService.getInstance("http://localhost:8080")).thenReturn(mockServer);

        assertEquals(0, summary.getTotalFails());
    }

    @SneakyThrows
    @Test
    void getOneJobBackTest() {

        Job job = mock(Job.class);
        JobWithDetails jobDetails = mock(JobWithDetails.class);
        Build build = mock(Build.class);
        BuildWithDetails buildDetails = mock(BuildWithDetails.class);
        when(build.details()).thenReturn(buildDetails);
        when(buildDetails.getTimestamp()).thenReturn(0L);
        BuildResult buildResult = mock(BuildResult.class);
        when(buildDetails.getResult()).thenReturn(buildResult);
        when(buildResult.name()).thenReturn("FAILURE");
        List<Build> buildList = Arrays.asList(build);
        Map<String, Job> jobs = new HashMap<>();
        jobs.put("myJob", job);
        TestResult result = mock(TestResult.class);
        when(result.getFailCount()).thenReturn(1);
        when(result.getPassCount()).thenReturn(2);
        when(result.getSkipCount()).thenReturn(5);

        when(mockJenkinsService.getInstance("http://localhost:8080")).thenReturn(mockServer);
        when(mockServer.getJobs()).thenReturn(jobs);
        when(jobDetails.getAllBuilds()).thenReturn(buildList);
        when(job.details()).thenReturn(jobDetails);
        when(build.getTestResult()).thenReturn(result);

        Map<String, HighestTestFail> map = new HashMap<>();
        HighestTestFail highestTestFail = new HighestTestFail("MyTestOne", "MyTestClassOne", "Product1", 1);
        List<TestSuites> suites = new ArrayList<>();
        when(mockJenkinsService.getFailingTests(suites, map)).thenReturn(map);
        when(mockJenkinsService.getMaxFailingTest(map)).thenReturn(highestTestFail);

        TestReportSummary summary = jenkinsRepository.getAllTestReports("http://localhost:8080", "myJob");

        int expectedTotalTests = 8;
        int expectedFails = 1;
        int expectedSkips = 5;
        String expectedJobName = "myJob";
        String expectedHighestFailTestName = "MyTestOne";

        assertEquals(expectedTotalTests, summary.getTotalTests());
        assertEquals(expectedFails, summary.getTotalFails());
        assertEquals(expectedSkips, summary.getTotalSkips());
        assertEquals(expectedJobName, summary.getReportName());
        assertEquals(expectedHighestFailTestName, summary.getHighestTestFail().getTestName());
    }

}
