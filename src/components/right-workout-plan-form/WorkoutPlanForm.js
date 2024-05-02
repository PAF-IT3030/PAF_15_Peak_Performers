import { toast } from "react-toastify";
import { craeteWorkoutPlans } from "../../util/APIUtils";
import "./WorkoutPlanForm.css";
import React, { useState } from "react";

function WorkoutPlanForm() {
  const [workoutPlan, setWorkoutPlan] = useState({
    planId: "",
    userId: "UID-202426001",
    name: "",
    description: "",
    routineDTOS: [],
    createdDate: new Date().toISOString(),
    updatedDate: new Date().toISOString(),
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setWorkoutPlan({ ...workoutPlan, [name]: value });
  };

  const handleRoutineChange = (index, event) => {
    const newRoutines = [...workoutPlan.routineDTOS];
    newRoutines[index][event.target.name] = event.target.value;
    setWorkoutPlan({ ...workoutPlan, routineDTOS: newRoutines });
  };

  const handleExerciseChange = (routineIndex, exerciseIndex, event) => {
    const updatedRoutines = workoutPlan.routineDTOS.map((routine, rIdx) => {
      if (rIdx === routineIndex) {
        const updatedExercises = routine.exerciseDTOS.map((exercise, eIdx) => {
          if (eIdx === exerciseIndex) {
            return { ...exercise, [event.target.name]: event.target.value };
          }
          return exercise;
        });
        return { ...routine, exerciseDTOS: updatedExercises };
      }
      return routine;
    });
    setWorkoutPlan({ ...workoutPlan, routineDTOS: updatedRoutines });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const workoutPlanRequest = Object.assign({}, workoutPlan);

    craeteWorkoutPlans(workoutPlanRequest)
      .then((response) => {
        console.log("Save workout plan success!")
        console.log(response)
        toast("You're successfully registered. Please login to continue!", {
          type: "success",
        });
      })
      .catch((error) => {
        toast(
          console.log("Save workout plan failed!: "+error)
          (error && error.message) ||
            "Oops! Something went wrong. Please try again!",
          { type: "error" }
        );
      });
  };

  const addRoutine = () => {
    setWorkoutPlan({
      ...workoutPlan,
      routineDTOS: [
        ...workoutPlan.routineDTOS,
        { routineId: "", name: "", exerciseDTOS: [] },
      ],
    });
  };

  const addExercise = (routineIndex) => {
    const updatedRoutines = workoutPlan.routineDTOS.map((routine, index) => {
      if (index === routineIndex) {
        return {
          ...routine,
          exerciseDTOS: [
            ...routine.exerciseDTOS,
            { exerciseId: "", name: "", sets: 0, repetitions: 0 },
          ],
        };
      }
      return routine;
    });
    setWorkoutPlan({ ...workoutPlan, routineDTOS: updatedRoutines });
  };

  const submitForm = (e) => {
    // e.preventDefault();
    handleSubmit(e);
    console.log(workoutPlan);
    // Here you would typically handle the submission to the backend
  };

  return (
    <form onSubmit={submitForm}>
      <input
        type="text"
        name="name"
        placeholder="Workout Plan Name"
        value={workoutPlan.name}
        onChange={handleInputChange}
      />
      <textarea
        name="description"
        placeholder="Workout Plan Description"
        value={workoutPlan.description}
        onChange={handleInputChange}
      />
      {workoutPlan.routineDTOS.map((routine, index) => (
        <div key={index}>
          <input
            type="text"
            name="name"
            placeholder="Routine Name"
            value={routine.name}
            onChange={(e) => handleRoutineChange(index, e)}
          />
          {routine.exerciseDTOS.map((exercise, eIndex) => (
            <div key={eIndex}>
              <input
                type="text"
                name="name"
                placeholder="Exercise Name"
                value={exercise.name}
                onChange={(e) => handleExerciseChange(index, eIndex, e)}
              />
              <input
                type="number"
                name="sets"
                placeholder="Sets"
                value={exercise.sets}
                onChange={(e) => handleExerciseChange(index, eIndex, e)}
              />
              <input
                type="number"
                name="repetitions"
                placeholder="Repetitions"
                value={exercise.repetitions}
                onChange={(e) => handleExerciseChange(index, eIndex, e)}
              />
            </div>
          ))}
          <button type="button" onClick={() => addExercise(index)}>
            Add Exercise
          </button>
        </div>
      ))}
      <button type="button" onClick={addRoutine}>
        Add Routine
      </button>
      <button type="submit">Save Workout Plan</button>
    </form>
  );
}

export default WorkoutPlanForm;
