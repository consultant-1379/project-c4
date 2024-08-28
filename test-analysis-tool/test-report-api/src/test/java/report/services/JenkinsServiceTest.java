package report.services;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.TestCase;
import com.offbytwo.jenkins.model.TestSuites;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import report.pojo.HighestTestFail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class JenkinsServiceTest {

    @Autowired
    JenkinsService jenkinsService;


    @SneakyThrows
    @Test
    void getNewInstanceTest() {
        String url = "localhost";
        String jobName = "jenkinsJob";
        jenkinsService.setJenkinsUrl(url);
        jenkinsService.setJobName(jobName);
        JenkinsServer jenkinsServer = jenkinsService.getInstance(url);
        assertThat(jenkinsServer, instanceOf(JenkinsServer.class));
        assertEquals(jenkinsService.getJenkinsUrl(), url);
        assertEquals(jenkinsService.getJobName(), jobName);
    }

    @Test
    void getFailingTestsTest() {
        TestCase failedTestCase = mock(TestCase.class);
        when(failedTestCase.getClassName()).thenReturn("MyTestClass");
        when(failedTestCase.getName()).thenReturn("MyTest");
        when(failedTestCase.getStatus()).thenReturn("FAILED");

        TestCase passedTestCase = mock(TestCase.class);
        when(passedTestCase.getStatus()).thenReturn("PASSED");
        TestSuites testSuites = mock(TestSuites.class);
        List<TestCase> cases = new ArrayList<>();
        cases.add(failedTestCase);
        cases.add(failedTestCase);
        List<TestSuites> suites = new ArrayList<>();
        suites.add(testSuites);
        when(testSuites.getCases()).thenReturn(cases);

        Map<String, HighestTestFail> map = new HashMap<>();
        map = jenkinsService.getFailingTests(suites, map);

        HighestTestFail expected = new HighestTestFail("MyTest", "MyTestClass", "", 2);
        HighestTestFail actual = map.get("MyTest");

        assertEquals(expected.getTestName(), actual.getTestName());
        assertEquals(expected.getNameOfClass(), actual.getNameOfClass());
        assertEquals(expected.getFails(), actual.getFails());
    }

    @Test
    void getMaxFailingTest() {
        HighestTestFail failOne = new HighestTestFail("MyTestOne", "MyTestClassOne", "Product1", 5);
        HighestTestFail failTwo = new HighestTestFail("MyTestTwo", "MyTestClassTwo", "Product1", 10);
        HighestTestFail failThree = new HighestTestFail("MyTestThree", "MyTestClassThree", "Product1", 1);
        Map<String, HighestTestFail> map = new HashMap<>();
        map.put(failOne.getTestName(), failOne);
        map.put(failTwo.getTestName(), failTwo);
        map.put(failThree.getTestName(), failThree);
        HighestTestFail expected = failTwo;

        HighestTestFail actual = jenkinsService.getMaxFailingTest(map);

        assertEquals(expected, actual);

    }
}
