import axios from "axios";
import userstore from './store/modules/userstore.js';
export default axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-type": "application/json",
        "jwt-auth-token": userstore.state.token,
        'asd':console.log(123)
    }
    
});
