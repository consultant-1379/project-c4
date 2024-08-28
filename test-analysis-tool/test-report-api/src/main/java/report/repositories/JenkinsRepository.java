package report.repositories;

import report.pojo.TestReportSummary;

public interface JenkinsRepository {

    TestReportSummary getAllTestReports(String jenkinsUrl, String jobName);
}
