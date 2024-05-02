import axios from "axios";

const Employee_Base_Url = "http://192.168.1.7:8080";
class PostService {
    getPosts(){
        return axios.get(`${Employee_Base_Url}/post/get-all`);
    }
    PostFormData(formData){
        axios.post(`${Employee_Base_Url}/post/add-post-image`,formData)
        .then(response=>{
          console.log(response);
        }).catch(error=>{
          console.log(error);
        })
    }
}
export default new PostService();