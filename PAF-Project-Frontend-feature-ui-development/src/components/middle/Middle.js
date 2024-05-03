import React, { useEffect, useState, useRef } from "react";
import "./Middle.css";
import axios from "axios";
import profilepic from "../../images/profile-1.jpg";
import PostService from "../../Services/PostService";
import { uploadImage } from "../../util/APIUtils";
import { toast } from "react-toastify";
export default function Middle() {
  const [posts, setPosts] = useState([]);

  const [file, setFile] = useState(null);

  const [imageUrl, setImageUrl] = useState("");
  const [description, setDescription] = useState("");

  const [display, setDisplay] = useState("none");

  const [mediaItems, setMediaItems] = useState([]);

  const onFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const onDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const onFileUpload = () => {
    const formData = new FormData();

    formData.append("file", file);
    formData.append("description", description);

    console.log(file);

    axios
      .post("http://localhost:8088/api/media/upload/image", formData)
      .then((response) => {
        console.log("File uploaded successfully", response);
        setImageUrl(response.data.data.url);
        toast("You're successfully image uploaded!", {
          type: "success",
        });
      })
      .catch((error) => {
        console.log("Error uploading file:", error);
        toast(
          (error && error.message) ||
            "Oops! Error uploading file:. Please try again!",
          { type: "error" }
        );
      });
  };

  const fileInputRef = useRef(null);
  // useEffect(()=>{

  // },[posts])

  const handleClick = () => {
    const fileInput = document.getElementById("file-input");
    fileInput.click();
  };

  const handleFileChange = (event) => {
    const file = event.target.files[0];

    if (file) {
      const reader = new FileReader();

      reader.onload = (event) => {
        setImageUrl(event.target.result);
      };

      reader.readAsDataURL(file);
      setDisplay("");
      setFile(event.target.files[0]);
    }
  };
  const handleClosePreview = () => {
    setImageUrl("");
    setDisplay("none");
    setFile(null);
    fileInputRef.current.value = ""; // Reset the file input value
  };

  useEffect(() => {
    axios
      .get("http://localhost:8088/api/media/all")
      .then((response) => {
        setMediaItems(response.data);
      })
      .catch((error) => console.error("Error fetching media:", error));
  }, []);

  return (
    <div className="middle">
      <div className="stories">
        <div className="story">
          <div className="profile-photo">
            <img src={profilepic} alt="" />
          </div>
          <p className="name">Darshan</p>
        </div>
        <div className="story">
          <div className="profile-photo">
            <img src="./images/profile-15.jpg" alt="" />
          </div>
          <p className="name">Ashu</p>
        </div>
        <div className="story">
          <div className="profile-photo">
            <img src="./images/profile-14.jpg" alt="" />
          </div>
          <p className="name">Shraddha</p>
        </div>
        <div className="story">
          <div className="profile-photo">
            <img src="./images/profile-13.jpg" alt="" />
          </div>
          <p className="name">Ananya</p>
        </div>
        <div className="story">
          <div className="profile-photo">
            <img src="./images/profile-12.jpg" alt="" />
          </div>
          <p className="name">Harsh</p>
        </div>
      </div>
      {/*-story ends here*/}
      <form onSubmit={onFileUpload} className="create-post">
        <div className="profile-photo">
          <img src={profilepic} alt="profile-photo" />
        </div>
        <input
          type="text"
          value={description}
          onChange={onDescriptionChange}
          // value={content}
          placeholder="what's on your mind Nishi?"
          id="create-post"
          // onChange={(event) => setContent(event.target.value)}
        />
        <div className="attach">
          <span>
            <i onClick={handleClick} className="uil uil-paperclip"></i>
            <input
              type="file"
              id="file-input"
              onChange={handleFileChange}
              ref={fileInputRef}
              style={{ display: "none" }}
            ></input>
          </span>
        </div>

        <input type="submit" defaultValue="post" className="btn btn-primary" />
      </form>
      <div id="preview" style={{ display }}>
        <span onClick={handleClosePreview}>
          <i className="uil uil-multiply"></i>
        </span>
        <img src={imageUrl}></img>
        {/* <div className="button"> <button type="submit" defaultValue="post" className="btn btn-primary" >Post</button></div> */}
      </div>

      {/*----------------Feeds-------------------*/}

      <div className="feeds">
        {mediaItems.map((post) => {
          return (
            <div className="feed" key={post.id}>
              <div className="head">
                <div className="user">
                  <div className="profile-photo">
                    <img src={profilepic} alt="profile-photo" />
                  </div>
                  <div className="info">
                    <h3>Lana Rose</h3>
                    <small>Dubai, 15 Minutes Ago</small>
                  </div>
                </div>

                <span className="edit">
                  <i className="uil uil-ellipsis-h" />
                </span>
              </div>
              <div className="content">
                <p>{post.description}</p>
              </div>
              <div className="photo">
                <img src={post.data} alt="" />
              </div>
              <div className="action-button">
                <div className="interation-buttons">
                  <span>
                    <i className="uil uil-heart" />
                  </span>
                  <span>
                    <i className="uil uil-comment-dots" />
                  </span>
                  <span>
                    <i className="uil uil-share-alt" />
                  </span>
                </div>
                <div className="bookmark">
                  <span>
                    <i className="uil uil-bookmark" />
                  </span>
                </div>
              </div>
              <div className="liked-by">
                <span>
                  <img src="./images/profile-11.jpg" />
                </span>
                <span>
                  <img src="./images/profile-11.jpg" />
                </span>
                <span>
                  <img src="./images/profile-11.jpg" />
                </span>
                <p>
                  Liked by <b>Earnest Achiever</b> and 323 others.
                </p>
              </div>
              <div className="caption">
                <p>
                  Lana Rose <b>Lorem ipsumm soluta officia non accusantium</b>{" "}
                  <span className="hashtag">#Lifestyle</span>
                </p>
              </div>
              <div className="comments text-muted"> view all 27 Coments</div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
