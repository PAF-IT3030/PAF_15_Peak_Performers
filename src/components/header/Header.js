import React from "react";
import "./Header.css";

import profileImage from "../../images/profile-1.jpg";
export default function Header({ authenticated, onLogout }) {
  return (
    <nav>
      <div className="container">
        <h2 className="logo">Vibe</h2>
        <div className="search-bar">
          <i className="uil uil-search" />
          <input
            type="search"
            placeholder="search for Creators, Inspirations and Projects"
          />
        </div>
        <div className="create">
          <label className="btn btn-primary" htmlFor="create-post">
            Create
          </label>
          <div className="profile-photo">
            <img src={profileImage} alt="profile1" />
          </div>
          <li>
            <a onClick={onLogout}>
              logout
            </a>
          </li>
        </div>
      </div>
    </nav>
  );
}
