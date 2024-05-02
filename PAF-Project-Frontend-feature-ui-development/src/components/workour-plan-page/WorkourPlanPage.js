import React, { useEffect, useState } from "react";
import MiddleWorkoutPlan from "../middleworkoutplan/MiddleWorkoutPlan";
import Left from "../left/Left";
import Right from "../right/Right";
import Header from "../header/Header";
import "./WorkourPlanPage.css";
import { toast } from "react-toastify";
import { useLocation, useNavigate } from "react-router-dom";
import { getAllWorkoutPlans } from "../../util/APIUtils";
import WorkoutPlanForm from "../right-workout-plan-form/WorkoutPlanForm";

export default function WorkourPlanPage({ authenticated, onLogout }) {
  // const [workoutPlans, setWorkoutPlans] = useState([]);

  // const fetchAllPost = async () => {
  //   try {
  //     const response = await getAllWorkoutPlans();
  //     setWorkoutPlans(response.data);
  //     console.log("fecthed success")
  //     console.log(response.data)
  //     console.log(workoutPlans) 
  //   } catch (error) {
  //     console.log("fecthed failed")
  //     console.log(error)
  //     toast("Oops something went wrong!", { type: "error",position: "bottom-right",
  //       autoClose: 5000,
  //       hideProgressBar: false,
  //       closeOnClick: true,
  //       pauseOnHover: true,
  //       draggable: true,
  //       progress: undefined,
  //       theme: "dark", });
  //   }
  // };

  // useEffect(() => {
  //   fetchAllPost();
  // }, []);

  return (
    <div>
      <Header authenticated={authenticated} onLogout={onLogout} />
      <main>
        <div className="container">
          <Left />
          <MiddleWorkoutPlan/>
          <WorkoutPlanForm />
        </div>
      </main>
    </div>
  );
}
