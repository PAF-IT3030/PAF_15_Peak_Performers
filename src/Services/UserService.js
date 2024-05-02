import axios from "axios";

const Employee_Base_Url = "http://192.168.1.7:8080";

class UserService {
  
  
    PostFormData(requestData){
      
        axios.post(`${Employee_Base_Url}/user/add-user`, requestData)
    .then(response => {
      console.log(response);
    }).catch(error => {
      console.log(error);
    })
    }
}
export default new UserService();