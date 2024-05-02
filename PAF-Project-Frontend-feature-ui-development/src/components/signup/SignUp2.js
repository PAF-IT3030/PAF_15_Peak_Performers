import React, { useEffect, useReducer } from "react";
import "./SignUp2.css";
import {
  MDBContainer,
  MDBCol,
  MDBRow,
  MDBBtn,
  MDBIcon,
  MDBInput,
  MDBCheckbox,
} from "mdb-react-ui-kit";
import backImage2 from "./../../images/img/bg2.png";
import { Link, Navigate, useLocation, useNavigate } from "react-router-dom";

import { ACCESS_TOKEN, GOOGLE_AUTH_URL } from "../../constants";
import { signup } from "../../util/APIUtils";

import { toast } from "react-toastify";

function SignUp2({ authenticated }) {
  const location = useLocation();
  const navigate = useNavigate();

  const [state, setState] = useReducer(
    (prevState, newState) => {
      return { ...prevState, ...newState };
    },
    {
      username: "",
      email: "",
      password: "",
    }
  );

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

    const signUpRequest = Object.assign({}, state);

    signup(signUpRequest)
      .then((response) => {
        toast("You're successfully registered. Please login to continue!", {
          type: "success",
        });

        navigate("/login");
      })
      .catch((error) => {
        toast(
          (error && error.message) ||
            "Oops! Something went wrong. Please try again!",
          { type: "error" }
        );
      });
  };

  // if (authenticated) {
  //   return (
  //     <Navigate
  //       to={{
  //         pathname: "/",
  //         state: { from: location },
  //       }}
  //     />
  //   );
  // }

  return (
    <MDBContainer fluid className="p-3 my-5 h-custom">
      <MDBRow>
        <MDBCol col="10" md="6">
          <img src={backImage2} class="img-fluid" alt="Sample image" />
        </MDBCol>

        <MDBCol col="4" md="6">
          <div className="d-flex flex-row align-items-center justify-content-center">
            <p className="lead fw-normal mb-0 me-3">Sign in with</p>

            <MDBBtn floating size="md" tag="a" className="me-2">
              <MDBIcon fab icon="facebook-f" />
            </MDBBtn>

            <MDBBtn floating size="md" tag="a" className="me-2">
              <MDBIcon fab icon="twitter" />
            </MDBBtn>

            <MDBBtn floating size="md" tag="a" className="me-2">
              <MDBIcon fab icon="linkedin-in" />
            </MDBBtn>
          </div>

          <div className="divider d-flex align-items-center my-4">
            <p className="text-center fw-bold mx-3 mb-0">Or</p>
          </div>

          <form onSubmit={handleSubmit}>
            <MDBInput
              wrapperClass="mb-4"
              label="User Name"
              id="formControlLg"
              type="text"
              name="username"
              placeholder="User Name"
              value={state.username}
              onChange={handleInputChange}
              required
              size="lg"
            />
            <MDBInput
              wrapperClass="mb-4"
              label="Email address"
              id="formControlLg"
              type="email"
              name="email"
              placeholder="Email"
              value={state.email}
              onChange={handleInputChange}
              required
              size="lg"
            />
            <MDBInput
              wrapperClass="mb-4"
              label="Password"
              id="formControlLg"
              type="password"
              name="password"
              placeholder="Password"
              value={state.password}
              onChange={handleInputChange}
              required
              size="lg"
            />
            <div className="d-flex justify-content-between mb-4">
              <MDBCheckbox
                name="flexCheck"
                value=""
                id="flexCheckDefault"
                label="Remember me"
              />
              <a href="!#">Forgot password?</a>
            </div>
            <div className="text-center text-md-start mt-4 pt-2">
              <MDBBtn type="submit" className="mb-0 px-5" size="lg">
                Sign up
              </MDBBtn>
              <p className="small fw-bold mt-2 pt-1 mb-2">
                Already having an account? <Link to="/login">Login!</Link>
              </p>
            </div>
          </form>
        </MDBCol>
      </MDBRow>

      <div className="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
        <div className="text-white mb-3 mb-md-0">
          Copyright © 2020. All rights reserved.
        </div>

        <div>
          <MDBBtn
            tag="a"
            color="none"
            className="mx-3"
            style={{ color: "white" }}
          >
            <MDBIcon fab icon="facebook-f" size="md" />
          </MDBBtn>

          <MDBBtn
            tag="a"
            color="none"
            className="mx-3"
            style={{ color: "white" }}
          >
            <MDBIcon fab icon="twitter" size="md" />
          </MDBBtn>

          <MDBBtn
            tag="a"
            color="none"
            className="mx-3"
            style={{ color: "white" }}
          >
            <MDBIcon fab icon="google" size="md" />
          </MDBBtn>

          <MDBBtn
            tag="a"
            color="none"
            className="mx-3"
            style={{ color: "white" }}
          >
            <MDBIcon fab icon="linkedin-in" size="md" />
          </MDBBtn>
        </div>
      </div>
    </MDBContainer>
  );
}

export default SignUp2;
