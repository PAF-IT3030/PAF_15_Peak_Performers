import "@fortawesome/fontawesome-free/css/all.min.css";
import "react-toastify/dist/ReactToastify.css";

import "./App.css";

import React, { useEffect, useReducer } from "react";
// import { Routes, Route } from "react-router-dom";

import MainPage from "./components/main-page/MainPage";
import SignUp from "./components/signup/SignUp2";
import LogIn from "./components/login/LogIn2";
import Left from "./components/left/Left";
import {
  BrowserRouter as Router,
  Routes as Switch,
  Route,
  useNavigate,
} from "react-router-dom";

import NotFound from "./common/NotFound";
import LoadingIndicator from "./common/LoadingIndicator";
import { getCurrentUser } from "./util/APIUtils";
import { ACCESS_TOKEN } from "./constants";
import PrivateRoute from "./common/PrivateRoute";

import { ToastContainer, toast } from "react-toastify";
import WorkourPlanPage from "./components/workour-plan-page/WorkourPlanPage";
import WorkourStatusPage from "./components/workour-status-page/WorkourStatusPage";
import MealPlanPage from "./components/meal-plan-page/MealPlanPage";

function App() {
  // const navigate = useNavigate();
  const [state, setState] = useReducer(
    (prevState, newState) => {
      return { ...prevState, ...newState };
    },
    {
      authenticated: true,
      currentUser: null,
      loading: true,
    }
  );

  const loadCurrentlyLoggedInUser = () => {
    getCurrentUser()
      .then((response) => {
        setState({
          currentUser: response,
          authenticated: false,
          loading: false,
        });
      })
      .catch((error) => {
        setState({
          loading: false,
        });
      });
  };

  const handleLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    setState({
      authenticated: false,
      currentUser: null,
    });
    toast("You're safely logged out!", {
      type: "success",
      position: "bottom-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "dark",
    });
    // navigate("/login");
  };

  useEffect(() => {
    loadCurrentlyLoggedInUser();
  }, []);

  if (state.loading) {
    return <LoadingIndicator />;
  }

  return (
    <div className="App">
      {/* <Router>
        <Switch>
          <Route path="/" element={<MainPage />} />
          <Route path="/sign-up" element={<SignUp />} />
          <Route path="/log-in" element={<LogIn />} />
          <Route path="/left" element={<Left />} />
        </Switch>
      </Router> */}

      <Router>
        <Switch>
          <Route
            path="/home"
            element={
              <PrivateRoute
                authenticated={state.authenticated}
                currentUser={state.currentUser}
                component={MainPage}
              />
            }
          />
          <Route
            path="/workoutplan"
            element={
              <PrivateRoute
                authenticated={state.authenticated}
                currentUser={state.currentUser}
                component={WorkourPlanPage}
              />
            }
          />
          <Route
            path="/workoutstatus"
            element={
              <PrivateRoute
                authenticated={state.authenticated}
                currentUser={state.currentUser}
                component={WorkourStatusPage}
              />
            }
          />
          <Route
            path="/mealplan"
            element={
              <PrivateRoute
                authenticated={state.authenticated}
                currentUser={state.currentUser}
                component={MealPlanPage}
              />
            }
          />
          <Route
            path="/login"
            element={<LogIn authenticated={state.authenticated} />}
          />
          <Route
            path="/signup"
            element={<SignUp authenticated={state.authenticated} />}
          />
          <Route
            path="/"
            element={
              <MainPage
                authenticated={state.authenticated}
                onLogout={handleLogout}
              />
            }
          />
        </Switch>
      </Router>
      <ToastContainer />
    </div>
  );
}

export default App;
