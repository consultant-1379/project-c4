package report.repositories;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import report.pojo.HighestTestFail;
import report.pojo.TestReportSummary;
import report.services.JenkinsService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;

@Repository
public class JenkinsRepositoryImpl implements JenkinsRepository {

    @Autowired
    JenkinsService jenkinsService;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public TestReportSummary getAllTestReports(String jenkinsUrl, String jobName) {
        TestReportSummary testReportSummary;
        int totalTests = 0;
        int totalPasses = 0;
        int totalFails = 0;
        int totalSkips = 0;
        long lastTestTime = 0;
        HighestTestFail failRate;
        Map<String, HighestTestFail> failures = new HashMap<>();
        try {
            JenkinsServer server = jenkinsService.getInstance(jenkinsUrl);
            Map<String, Job> jobs = server.getJobs();
            Job job = jobs.get(jobName);
            JobWithDetails jobDetails = job.details();
            List<Build> builds = jobDetails.getAllBuilds();
            if (!builds.isEmpty()){
                Build build = builds.get(0);
                lastTestTime = build.details().getTimestamp();
            }
            for (Build build : builds) {
                TestResult apiTestResult = build.getTestResult();
                totalTests += apiTestResult.getFailCount() + apiTestResult.getPassCount() + apiTestResult.getSkipCount();
                totalPasses += apiTestResult.getPassCount();
                totalFails += apiTestResult.getFailCount();
                totalSkips += apiTestResult.getSkipCount();
                if(build.details().getResult().name().equals("FAILURE")){
                    failures = jenkinsService.getFailingTests(apiTestResult.getSuites(), failures);
                }
            }
        } catch (URISyntaxException | IOException e) {
            logger.warning(e.getMessage());
        }
        failRate = jenkinsService.getMaxFailingTest(failures);
        failRate.setProduct(jobName);
        testReportSummary = new TestReportSummary(jobName, totalTests, totalPasses, totalFails, totalSkips, lastTestTime, failRate);
        return testReportSummary;
    }
}
