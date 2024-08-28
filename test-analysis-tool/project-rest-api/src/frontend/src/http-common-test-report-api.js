import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:9090/testReport/",
    headers: {
        "Content-type": "application/json"
    }
});