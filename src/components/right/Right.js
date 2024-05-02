import React from "react";
import "./Right.css";

export default function Right() {
  return (
    <div className="right">
      <div className="messages">
        <div className="heading">
          <h4>Messages</h4>
          <i className="uil uil-edit" />
        </div>
        <div className="search-bar">
          <i className="uil uil-search" />
          <div className="strech">
            <input
              type="search"
              placeholder="search messages"
              id="message-search"
              className="resizedTextbox"
            />
          </div>
        </div>
        {/*category*/}
        <div className="category">
          <h6 className="active">Primary</h6>
          <h6>General</h6>
          <h6 className="message-requests">Requests(7)</h6>
        </div>
        <div className="message">
          <div className="profile-photo">
            <img src="./images/profile-11.jpg" alt="" />
            <div className="active" />
          </div>
          <div className="message-body">
            <h5>Siddhartha Sharma</h5>
            <p className="text-bold">just woke up bruh</p>
          </div>
        </div>
      </div>
      {/*end of message*/}
      {/*-Friend Reuqests*/}
      <div className="friend-requests">
        <h4>Requests</h4>
        <div className="request">
          <div className="info">
            <div className="profile-photo">
              <img src="./images/profile-14.jpg" />
            </div>
            <div>
              <h5>Sonalika Bharadwaj</h5>
              <p className="text-muted"> 8 Mutual Friends</p>
            </div>
          </div>
          <div className="action">
            <button className="btn btn-primary">Accept</button>
            <button className="btn">Decline</button>
          </div>
        </div>
      </div>
    </div>
  );
}
