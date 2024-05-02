import React from "react";
import "./Left.css";
import profilepic from "../../images/profile-1.jpg";
import { Link } from "react-router-dom";
export default function Left() {
  return (
    <div className="left">
      <a href="" className="profile">
        <div className="profile-photo">
          <img src={profilepic} alt="profile-picture" />
        </div>
        <div className="handle">
          <h4>Nishi Jain</h4>
          <p className="text-muted">@nishi</p>
        </div>
      </a>
      {/*sidebar*/}
      <div className="sidebar">
        <a className="menu-item active">
          <span>
            <i className="uil uil-home" />{" "}
          </span>
          <Link to="/">
            <h3>Home</h3>
          </Link>
        </a>
        <a className="menu-item">
          <span>
            <i className="uil uil-compass" />{" "}
          </span>
          <Link to="/workoutplan">
            <h3>Workout Plan</h3>
          </Link>
        </a>
        <a className="menu-item" id="notifications">
          <span>
            <i className="uil uil-bell">
              <small className="notification-count">9+</small>
            </i>{" "}
          </span>
          <Link to="/workoutstatus">
            <h3>Workout Staus</h3>
          </Link>
          {/*notification popup*/}
          <div className="notifications-popup">
            <div>
              <div className="profile-photo">
                <img src="./images/profile-2.jpg" alt="profile-picture" />
              </div>
              <div className="notification-body">
                <b>Siddhartha Sharma</b> accepted your friend Request.
                <small className="text-muted">2 Days Ago</small>
              </div>
            </div>
            <div>
              <div className="profile-photo">
                <img src="./images/profile-3.jpg" alt="profile-picture" />
              </div>
              <div className="notification-body">
                <b>John Doe</b> commented On your post.
                <small className="text-muted">2 Days Ago</small>
              </div>
            </div>
            <div>
              <div className="profile-photo">
                <img src="./images/profile-2.jpg" alt="profile-picture" />
              </div>
              <div className="notification-body">
                <b>keke benjamin</b> Accepted your Friend Request.
                <small className="text-muted">2 Days Ago</small>
              </div>
            </div>
            <div>
              <div className="profile-photo">
                <img src="./images/profile-2.jpg" alt="profile-picture" />
              </div>
              <div className="notification-body">
                <b>keke benjamin</b> Accepted your Friend Request.
                <small className="text-muted">2 Days Ago</small>
              </div>
            </div>
            <div>
              <div className="profile-photo">
                <img src="./images/profile-2.jpg" alt="profile-picture" />
              </div>
              <div className="notification-body">
                <b>keke benjamin</b> Accepted your Friend Request.
                <small className="text-muted">2 Days Ago</small>
              </div>
            </div>
            <div>
              <div className="profile-photo">
                <img src="./images/profile-2.jpg" alt="profile-picture" />
              </div>
              <div className="notification-body">
                <b>keke benjamin</b> Accepted your Friend Request.
                <small className="text-muted">2 Days Ago</small>
              </div>
            </div>
          </div>
        </a>
        <a className="menu-item" id="messages-notifications">
          <span>
            <i className="uil uil-envelope">
              {" "}
              <small className="notification-count">7</small>
            </i>{" "}
          </span>
          <Link to="/mealplan">
            <h3>Meal plan</h3>
          </Link>
        </a>
        <a className="menu-item">
          <span>
            <i className="uil uil-bookmark" />{" "}
          </span>
          <h3>BookMarks</h3>
        </a>
        <a className="menu-item">
          <span>
            <i className="uil uil-chart-line" />{" "}
          </span>
          <h3>Analytics</h3>
        </a>
        <a className="menu-item">
          <span>
            <i className="uil uil-palette" />{" "}
          </span>
          <h3>Theme</h3>
        </a>
        <a className="menu-item">
          <span>
            <i className="uil uil-setting" />{" "}
          </span>
          <h3>Settings</h3>
        </a>
        {/*End Of Sidebar*/}
        <label htmlFor="create-post" className="btn btn-primary">
          Create Post
        </label>
      </div>
    </div>
  );
}
