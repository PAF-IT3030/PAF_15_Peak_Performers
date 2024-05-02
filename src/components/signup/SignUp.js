import React from "react";
import styles from "./SignUp.css";
import { useFormik } from "formik";
import { signUpSchema } from "../Schemas";
import UserService from "../../Services/UserService";
export default function SignUp() {
  const { values, errors, handleBlur, handleChange, handleSubmit } = useFormik({
    initialValues: {
      fullName: "",
      userName: "",
      email: "",
      phone: "",
      pass: "",
      pass2: "",
      gender: "",
    },
    validationSchema: signUpSchema,
    onSubmit: (values) => {
      const requestData = {
        fullName: values.fullName,
        userName: values.userName,
        email: values.email,
        gender: values.gender.toUpperCase(),
        phone: values.phone,
        password: values.pass
      };
      

      console.log(values);
      UserService.PostFormData(requestData);
    },
  });
  return (
    <div className="signup">
      {/* Created By CodingLab - www.codinglabweb.com */}
      <meta charSet="UTF-8" />
      {/*-<title> Responsive Registration Form | CodingLab </title>-*/}

      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <div className="container">
        <div className="title">Sign Up</div>
        <div className="content">
          <form
            onSubmit={(event) => {
              event.preventDefault();
              handleSubmit();
            }}
          >
            <div className="user-details">
              <div className="input-box">
                <span className="details">Full Name</span>
                <input
                  type="text"
                  value={values.fullName}
                  onChange={handleChange}
                  placeholder="Enter your name"
                  name="fullName"
                  required=""
                />
                <p className="form-error">{errors.fullName}</p>
              </div>
              <div className="input-box">
                <span className="details">Username</span>
                <input
                  type="text"
                  name="userName"
                  placeholder="Enter your username"
                  value={values.userName}
                  onChange={handleChange}
                  onBlur={handleBlur}
                  required=""
                />
                <p className="form-error">{errors.userName}</p>
              </div>
              <div className="input-box">
                <span className="details">Email</span>
                <input
                  type="email"
                  value={values.email}
                  onChange={handleChange}
                  onBlur={handleBlur}
                  placeholder="Enter your email"
                  name="email"
                  required=""
                />
                <p className="form-error">{errors.email}</p>
              </div>
              <div className="input-box">
                <span className="details">Phone Number</span>
                <input
                  type="text"
                  placeholder="Enter your number"
                  value={values.phone}
                  onChange={handleChange}
                  onBlur={handleBlur}
                  name="phone"
                  required=""
                />
                <p className="form-error">{errors.phone}</p>
              </div>
              <div className="input-box">
                <span className="details">Password</span>
                <input
                  type="password"
                  name="pass"
                  value={values.pass}
                  onChange={handleChange}
                  onBlur={handleBlur}
                  placeholder="Enter your password"
                  required=""
                />
                <p className="form-error">{errors.pass}</p>
              </div>
              <div className="input-box">
                <span className="details">Confirm Password</span>
                <input
                  type="password"
                  name="pass2"
                  value={values.pass2}
                  onChange={handleChange}
                  onBlur={handleBlur}
                  placeholder="Confirm your password"
                  required=""
                />
                <p className="form-error">{errors.pass2}</p>
              </div>
            </div>
            <div className="gender-details">
              <input
                type="radio"
                name="gender"
                value="male"
                id="dot-1"
                onChange={handleChange}
                onBlur={handleBlur}
                checked={values.gender === "male"}
              />

              <input
                type="radio"
                name="gender"
                value="female"
                id="dot-2"
                onChange={handleChange}
                onBlur={handleBlur}
                checked={values.gender === "female"}
              />

              <span className="gender-title">Gender</span>
              <div className="category">
                <label htmlFor="dot-1">
                  <span className="dot one" />
                  <span className="gender">Male</span>
                </label>
                <label htmlFor="dot-2">
                  <span className="dot two" />
                  <span className="gender">Female</span>
                </label>
              </div>
              <p className="form-error">{errors.gender}</p>
            </div>
            <div className="button">
              <input type="submit" defaultValue="Register" />
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
