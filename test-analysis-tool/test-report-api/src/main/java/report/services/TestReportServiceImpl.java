package report.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import report.pojo.TestReportSummary;
import report.repositories.JenkinsRepository;

import java.util.List;

@Component
public class TestReportServiceImpl implements TestReportService {

    @Autowired
    JenkinsRepository jenkinsRepository;

    @Override
    public TestReportSummary getAllReportsForCNA(String jenkinsUrl, String jobName) {
        return jenkinsRepository.getAllTestReports(jenkinsUrl, jobName);
    }

    @Override
    public TestReportSummary getAllReportsForProject(String jenkinsUrl, List<String> jobNames) {
        TestReportSummary totalSummary = new TestReportSummary();
        for (String currentJob : jobNames) {
            TestReportSummary current = jenkinsRepository.getAllTestReports(jenkinsUrl, currentJob);
            totalSummary.setTotalTests(totalSummary.getTotalTests() + current.getTotalTests());
            totalSummary.setTotalPasses(totalSummary.getTotalPasses() + current.getTotalPasses());
            totalSummary.setTotalFails(totalSummary.getTotalFails() + current.getTotalFails());
            totalSummary.setTotalSkips(totalSummary.getTotalSkips() + current.getTotalSkips());
            totalSummary.setLastTestTime(Math.max(totalSummary.getLastTestTime(), current.getLastTestTime()));
            if (totalSummary.getHighestTestFail().getFails() < current.getHighestTestFail().getFails()) {
                totalSummary.setHighestTestFail(current.getHighestTestFail());
            }
        }
        return totalSummary;
    }
}
