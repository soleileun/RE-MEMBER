import axios from "axios";

export default axios.create({
    baseURL: "i3a208.p.ssafy.io:8080",
    headers: {
        "Content-type": "application/json",
    }
});
