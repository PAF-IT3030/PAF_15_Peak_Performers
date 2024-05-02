import React, { useEffect, useState, useRef } from "react";
import "./MiddleWorkoutPlan.css";
import axios from "axios";
import profilepic from "../../images/profile-1.jpg";
import PostService from "../../Services/PostService";
import profile1 from "../../images/profile-11.jpg";
import profile2 from "../../images/profile-12.jpg";
import profile3 from "../../images/profile-13.jpg";
export default function MiddleWorkoutPlan({ data }) {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    PostService.getPosts().then((res) => {
      setPosts(res.data);
    });
  }, [posts]);

  const [file, setFile] = useState(null);

  const [content, setContent] = useState("");

  const [imageUrl, setImageUrl] = useState("");

  const [display, setDisplay] = useState("none");

  const fileInputRef = useRef(null);
  useEffect(() => {}, [posts]);
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
  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append("content", content);
    formData.append("file", file);

    setImageUrl("");
    setDisplay("none");
    setFile(null);
    fileInputRef.current.value = ""; // Reset the file input value
    setContent("");

    PostService.PostFormData(formData);
  };

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
            <img src={profile1} alt="" />
          </div>
          <p className="name">Ashu</p>
        </div>
        <div className="story">
          <div className="profile-photo">
            <img src={profile2} alt="" />
          </div>
          <p className="name">Shraddha</p>
        </div>
        <div className="story">
          <div className="profile-photo">
            <img src={profile3} alt="" />
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
      <form
        action="create-post"
        onSubmit={handleSubmit}
        className="create-post"
      >
        <div className="profile-photo">
          <img src={profilepic} alt="profile-photo" />
        </div>
        <input
          type="text"
          value={content}
          placeholder="what's on your mind Nishi?"
          id="create-post"
          onChange={(event) => setContent(event.target.value)}
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

        <input
          type="submit"
          onClick={handleSubmit}
          defaultValue="post"
          className="btn btn-primary"
        />
      </form>
      <div id="preview" style={{ display }}>
        <span onClick={handleClosePreview}>
          <i className="uil uil-multiply"></i>
        </span>
        <img src={imageUrl}></img>

        <div className="button">
          {" "}
          <button type="submit" defaultValue="post" className="btn btn-primary">
            Post
          </button>
        </div>
      </div>

      {/*----------------Feeds-------------------*/}

      <div className="feeds">
        {data.map((post) => {
          return (
            <div className="feed">
              <div className="head">
                <div className="user">
                  <div className="profile-photo">
                    <img src={profilepic} alt="profile-photo" />
                  </div>
                  <div className="info">
                    <h3>{post.name}</h3>
                    <small>{post.createdDate}</small>
                  </div>
                </div>

                <span className="edit">
                  <i className="uil uil-ellipsis-h" />
                </span>
              </div>
              <div className="content">{post.description}</div>
              <div className="photo">
                {/* <img src={post.image} alt="" /> */}
              </div>
              <div
                className="caption"
                style={{
                  backgroundColor: "#f8f9fa",
                  padding: "15px",
                  borderRadius: "8px",
                  boxShadow: "0 2px 4px rgba(0,0,0,0.1)",
                }}
              >
                <h6
                  style={{
                    borderBottom: "2px solid #007bff",
                    paddingBottom: "10px",
                    marginBottom: "10px",
                    color: "#007bff",
                  }}
                >
                  <b>Routing plans:</b>
                </h6>
                {post.routineDTOS.map((routine) => (
                  <div key={routine.routineId} style={{ marginBottom: "10px" }}>
                    <h6
                      style={{
                        color: "#333",
                        fontSize: "16px",
                        fontWeight: "bold",
                        marginBottom: "5px",
                      }}
                    >
                      {routine.name}
                    </h6>
                    <ul style={{ listStyleType: "none", paddingLeft: "20px" }}>
                      {routine.exerciseDTOS.map((exercise) => (
                        <li
                          key={exercise.exerciseId}
                          style={{
                            marginBottom: "10px",
                            padding: "5px",
                            borderRadius: "5px",
                            backgroundColor: "#fff",
                            boxShadow: "0 1px 3px rgba(0,0,0,0.05)",
                          }}
                        >
                          <span style={{ fontWeight: "bold" }}>
                            {exercise.name}
                          </span>
                          <ul
                            style={{
                              listStyleType: "square",
                              marginLeft: "20px",
                              color: "#555",
                            }}
                          >
                            <li>Sets: {exercise.sets}</li>
                            <li>Repetitions: {exercise.repetitions}</li>
                          </ul>
                        </li>
                      ))}
                    </ul>
                  </div>
                ))}
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
                  <img src={profile1} />
                </span>
                <span>
                  <img src={profile2} />
                </span>
                <span>
                  <img src={profile3} />
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
              <div className="comments text-muted"> view all 277 Coments</div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
