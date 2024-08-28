package report.services;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.TestCase;
import com.offbytwo.jenkins.model.TestSuites;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import report.pojo.HighestTestFail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
public class JenkinsService {

    @Getter
    @Setter
    private String jenkinsUrl;

    @Getter
    @Setter
    private String jobName;

    public JenkinsServer getInstance(String jenkinsUrl) throws URISyntaxException {
        return new JenkinsServer(new URI(jenkinsUrl), "enahsik", "1234");
    }

    public Map<String, HighestTestFail> getFailingTests(List<TestSuites> suites, Map<String, HighestTestFail> numberOfFails) {
        for (TestSuites suite : suites) {
            for (TestCase testCase : suite.getCases()) {
                if (testCase.getStatus().equals("FAILED")) {
                    HighestTestFail current = numberOfFails.getOrDefault(testCase.getName(),
                            new HighestTestFail(testCase.getName(), testCase.getClassName(), "", 0));
                    current.setFails(current.getFails() + 1);
                    numberOfFails.put(testCase.getName(), current);
                }
            }
        }
        return numberOfFails;
    }

    public HighestTestFail getMaxFailingTest(Map<String, HighestTestFail> failures) {
        HighestTestFail maxFail = new HighestTestFail();
        for (Map.Entry<String, HighestTestFail> current : failures.entrySet()) {
            if (current.getValue().getFails() >= maxFail.getFails())
                maxFail = current.getValue();
        }
        return maxFail;
    }
}
