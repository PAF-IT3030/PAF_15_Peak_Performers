import React, { useEffect, useState, useRef } from "react";
import "./MiddleMealPlan.css";
import axios from "axios";
import profilepic from "../../images/profile-1.jpg";
import PostService from "../../Services/PostService";
import profile1 from "../../images/profile-11.jpg";
import profile2 from "../../images/profile-12.jpg";
import profile3 from "../../images/profile-13.jpg";
import { deleteMealPlanById, getAllMealPlans } from "../../util/APIUtils";
import { toast } from "react-toastify";
export default function MiddleMealPlan() {
  const [mealPlans, setMealtPlans] = useState([]);

  const fetchAllPost = async () => {
    try {
      const response = await getAllMealPlans();
      setMealtPlans(response.data);
      console.log("fecthed success");
      console.log(response.data);
      console.log(mealPlans);
    } catch (error) {
      console.log("fecthed failed");
      console.log(error);
      toast("Oops something went wrong!", {
        type: "error",
        position: "bottom-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "dark",
      });
    }
  };

  useEffect(() => {
    fetchAllPost();
  }, []);

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

  const handleDelete = (planId) => {
    // preventDefault();

    deleteMealPlanById(planId)
      .then((response) => {
        console.log(planId);
        console.log("Delete meal plan success!", response);
        toast("Meal plan deleted successfully!", { type: "success" });
        refreshComponent();
      })
      .catch((error) => {
        console.error("Delete meal plan failed:", error);
        toast(
          error && error.message
            ? error.message
            : "Oops! Something went wrong. Please try again!",
          { type: "error" }
        );
      });
  };

  const refreshComponent = () => {
    fetchAllPost();
  };

  return (
    <div className="middle">
      <div className="storiesMP">
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
          <p className="name">Sameera</p>
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
          <p className="name">Kumar</p>
        </div>
      </div>
      {/*-story ends here*/}
      <form
        // action="create-post"
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
        {mealPlans.map((post) => {
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
                <span
                  className="edit"
                  onClick={() => handleDelete(post.mealPlanId)}
                  title="Delete Meal Plan"
                >
                  <i className="uil uil-trash-alt"></i>
                </span>
                <span className="edit">
                  <i className="uil uil-ellipsis-h" />
                </span>
              </div>
              <div className="content">{post.description}</div>
              <div className="photo">{/* <img src={profile2} alt="" /> */}</div>
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
                {post.recipes.map((recipe) => (
                  <div key={recipe.recipeId} className="recipe-card">
                    <h4 className="recipe-title">{recipe.name}</h4>
                    <img
                      src={recipe.photoUrl}
                      alt={recipe.name}
                      className="recipe-image"
                    />
                    <div className="recipe-details">
                      <h5>Ingredients:</h5>
                      <ul className="ingredients-list">
                        {recipe.ingredients.map((ingredient, index) => (
                          <li key={index} className="ingredient-item">
                            {ingredient.name}: {ingredient.quantity}
                          </li>
                        ))}
                      </ul>
                      <p className="instructions">{recipe.instructions}</p>
                      <div className="nutrition-info">
                        <span>Calories: {recipe.nutrition.calories}</span>
                        <span>Protein: {recipe.nutrition.protein}g</span>
                        <span>Carbs: {recipe.nutrition.carbs}g</span>
                        <span>Fat: {recipe.nutrition.fat}g</span>
                      </div>
                      <div className="dietary-preferences">
                        {recipe.dietaryPreferences.map((preference, index) => (
                          <span key={index} className="dietary-tag">
                            {preference}
                          </span>
                        ))}
                      </div>
                    </div>
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
