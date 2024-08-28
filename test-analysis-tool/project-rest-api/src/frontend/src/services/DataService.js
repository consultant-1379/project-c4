import projectHTTP from "../http-common-project-rest-api";
import testHTTP from "../http-common-test-report-api";

class DataService {
    getAllProjects() {
        return projectHTTP.get("/get-all-projects");
    }

    addProject(projectData) {
        return projectHTTP.post("/add-project", projectData);
    }

    getAllReportsForCNA(projectData) {
        return testHTTP.get(`/getAllReportsForCNA/${projectData.jobName}?jenkinsUrl=${projectData.jenkinsUrl}`);
    }

    getAllReportsForProjectENM(projectData) {
        return testHTTP.post(`/getAllReportsForProject/${projectData.jobName}/?jenkinsUrl=${projectData.jenkinsUrl}`, projectData.jobNames);
    }
}

export default new DataService();