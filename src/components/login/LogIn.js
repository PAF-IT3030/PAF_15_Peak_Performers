import React, { useEffect, useReducer } from "react";
// import styles from "./../signup/SignUp.css";
import "./LogIn.css"
import googleLogo from "./../../images/img/google-logo.png";
// import {toast} from "react-toastify";
import {Link, Navigate, useLocation, useNavigate} from "react-router-dom";


export default function SignUp() {
  return (
    <div className="sigxxzxnup">
      {/* Created By CodingLab - www.codinglabweb.com */}
      <meta charSet="UTF-8" />
      -<title> Responsive Registration Form | CodingLab </title>-
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <div
        className="login-container h-screen"
        style={{
          // backgroundImage: "url('https://i.imgur.com/ayoHSpM.jpg')",
          // backgroundSize: "cover",
        }}
      >
        <div className="login-content bg-gray-50">
          <h1 className="login-title">Login</h1>
          <h4 className="login-mid-title">
            connect with foodies around the world!
          </h4>
          <SocialLogin />
          <div className="or-separator">
            <span className="or-text bg-gray-600">OR</span>
          </div>
          <LoginForm />
          <span className="signup-link">
            Want to join our foodie community?{" "}
            <Link to="/signup">Register here!</Link>
          </span>
        </div>
      </div>
    </div>
  );
}

const SocialLogin = () => {
  return (
    <div className="social-login">
      <a
        className="btn btn-block social-btn google bg-gray-200"
        // href={GOOGLE_AUTH_URL}
        style={{ color: "rgba(255, 255, 255, 0.65)" }}
      >
        <img src={googleLogo} alt="Google" /> Sign in with Google
      </a>
    </div>
  );
};

const LoginForm = () => {
  const [state, setState] = useReducer(
    (prevState, newState) => {
      return { ...prevState, ...newState };
    },
    {
      email: "",
      password: "",
    }
  );
  const navigate = useNavigate();

  const handleInputChange = (event) => {
    const target = event.target;
    const inputName = target.name;
    const inputValue = target.value;

    setState({
      [inputName]: inputValue,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const loginRequest = Object.assign({}, state);

    // login(loginRequest)
    //   .then((response) => {
    //     localStorage.setItem(ACCESS_TOKEN, response.accessToken);
    //     toast("You're successfully logged in!", { type: "success" });

    //     navigate("/");
    //   })
    //   .catch((error) => {
    //     toast(
    //       (error && error.message) ||
    //         "Oops! Something went wrong. Please try again!",
    //       { type: "error" }
    //     );
    //   });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="form-item">
        <input
          type="email"
          name="email"
          className="form-control"
          placeholder="Email"
          value={state.email}
          onChange={handleInputChange}
          required
        />
      </div>
      <div className="form-item">
        <input
          type="password"
          name="password"
          className="form-control"
          placeholder="Password"
          value={state.password}
          onChange={handleInputChange}
          required
        />
      </div>
      <div className="form-item">
        <button type="submit" className="btn btn-block btn-primary">
          Login
        </button>
      </div>
    </form>
  );
};
