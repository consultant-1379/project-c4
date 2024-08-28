package report.services;

import report.pojo.TestReportSummary;

import java.util.List;

public interface TestReportService {

    TestReportSummary getAllReportsForCNA(String jenkinsUrl, String jobName);

    TestReportSummary getAllReportsForProject(String jenkinsUrl, List<String> jobNames);
}
