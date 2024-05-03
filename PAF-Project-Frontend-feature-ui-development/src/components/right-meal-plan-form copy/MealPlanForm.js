import { toast } from "react-toastify";
import { craeteMealPlans } from "../../util/APIUtils";
import "./MealPlanForm.css";
import React, { useState } from "react";

function MealPlanForm() {
  const [mealPlan, setMealPlan] = useState({
    mealPlanId: "",
    userId: "UID-202426001",
    name: "",
    description: "",
    recipes: []
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setMealPlan({ ...mealPlan, [name]: value });
  };

  // const handleRecipeChange = (index, event) => {
  //   const newRecipes = [...mealPlan.recipes];
  //   newRecipes[index] = { ...newRecipes[index], [event.target.name]: event.target.value };
  //   setMealPlan({ ...mealPlan, recipes: newRecipes });
  // };

  const handleRecipeChange = (recipeIndex, event) => {
    const updatedRecipes = [...mealPlan.recipes];
    if (event.target.name in updatedRecipes[recipeIndex].nutrition) {
      updatedRecipes[recipeIndex].nutrition[event.target.name] = event.target.value;
    } else {
      updatedRecipes[recipeIndex][event.target.name] = event.target.value;
    }
    setMealPlan({ ...mealPlan, recipes: updatedRecipes });
  };

  const handleIngredientChange = (recipeIndex, ingredientIndex, event) => {
    const updatedRecipes = mealPlan.recipes.map((recipe, rIndex) => {
      if (rIndex === recipeIndex) {
        const updatedIngredients = recipe.ingredients.map((ingredient, iIndex) => {
          if (iIndex === ingredientIndex) {
            return { ...ingredient, [event.target.name]: event.target.value };
          }
          return ingredient;
        });
        return { ...recipe, ingredients: updatedIngredients };
      }
      return recipe;
    });
    setMealPlan({ ...mealPlan, recipes: updatedRecipes });
  };

  const addRecipe = () => {
    const newRecipe = {
      recipeId: "",
      name: "",
      ingredients: [],
      instructions: "",
      photoUrl: "",
      nutrition: { calories: 0, protein: 0, carbs: 0, fat: 0 },
      portionSize: "",
      dietaryPreferences: []
    };
    setMealPlan({ ...mealPlan, recipes: [...mealPlan.recipes, newRecipe] });
  };

  const addIngredient = (recipeIndex) => {
    const updatedRecipes = mealPlan.recipes.map((recipe, index) => {
      if (index === recipeIndex) {
        return {
          ...recipe,
          ingredients: [...recipe.ingredients, { name: "", quantity: "" }]
        };
      }
      return recipe;
    });
    setMealPlan({ ...mealPlan, recipes: updatedRecipes });
  };

  const addDietaryPreference = (recipeIndex) => {
    const updatedRecipes = [...mealPlan.recipes];
    updatedRecipes[recipeIndex].dietaryPreferences.push("");
    setMealPlan({ ...mealPlan, recipes: updatedRecipes });
  };

  const handleDietaryPreferenceChange = (recipeIndex, index, event) => {
      const updatedRecipes = [...mealPlan.recipes];
      updatedRecipes[recipeIndex].dietaryPreferences[index] = event.target.value;
      setMealPlan({ ...mealPlan, recipes: updatedRecipes });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const mealPlanRequest = Object.assign({}, mealPlan);

    craeteMealPlans(mealPlanRequest)
      .then((response) => {
        console.log("Save meal plan success!")
        console.log(response)
        toast("Workout meal created successfully", {
          type: "success",
        });
      })
      .catch((error) => {
        toast(
          console.log("Save meal plan failed!: "+error)
          (error && error.message) ||
            "Oops! Something went wrong. Please try again!",
          { type: "error" }
        );
      });
  };

  return (
    <form onSubmit={handleSubmit} className="meal-plan-form">
      <h2>Create/Edit Meal Plan</h2>
      <label>
        Meal Plan Name:
        <input type="text" name="name" value={mealPlan.name} onChange={handleInputChange} />
      </label>
      <label>
        Description:
        <textarea name="description" value={mealPlan.description} onChange={handleInputChange} />
      </label>
      {mealPlan.recipes.map((recipe, index) => (
        <div key={index} className="recipe-section">
          <h3>Recipe {index + 1}</h3>
          <label>
            Recipe Name:
            <input type="text" name="name" value={recipe.name} onChange={(e) => handleRecipeChange(index, e)} />
          </label>
          {recipe.ingredients.map((ingredient, iIndex) => (
            <div key={iIndex} className="ingredient-section">
              <label>
                Ingredient Name:
                <input type="text" name="name" value={ingredient.name} onChange={(e) => handleIngredientChange(index, iIndex, e)} />
              </label>
              <label>
                Quantity:
                <input type="text" name="quantity" value={ingredient.quantity} onChange={(e) => handleIngredientChange(index, iIndex, e)} />
              </label>
            </div>
          ))}
          <button type="button" onClick={() => addIngredient(index)}>Add Ingredient</button>
          <label>
            Instructions:
            <textarea name="instructions" value={recipe.instructions} onChange={(e) => handleRecipeChange(index, e)} />
          </label>
          <div className="nutrition-section">
              <label>Calories:
                  <input type="number" name="calories" value={recipe.nutrition.calories} onChange={(e) => handleRecipeChange(index, e)} />
              </label>
              <label>Protein (g):
                  <input type="number" name="protein" value={recipe.nutrition.protein} onChange={(e) => handleRecipeChange(index, e)} />
              </label>
              <label>Carbs (g):
                  <input type="number" name="carbs" value={recipe.nutrition.carbs} onChange={(e) => handleRecipeChange(index, e)} />
              </label>
              <label>Fat (g):
                <input type="number" name="fat" value={recipe.nutrition.fat} onChange={(e) => handleRecipeChange(index, e)} />
              </label>
          </div>
          <label>
              Portion Size:
              <input type="text" name="portionSize" value={recipe.portionSize} onChange={(e) => handleRecipeChange(index, e)} />
          </label>
                    {recipe.dietaryPreferences.map((preference, pIndex) => (
                        <div key={pIndex}>
                            <input type="text" value={preference} onChange={(e) => handleDietaryPreferenceChange(index, pIndex, e)} />
                            <button type="button" onClick={() => addDietaryPreference(index)}>Add Dietary Preference</button>
                        </div>
                    ))}
        </div>
      ))}
      <button type="button" onClick={addRecipe}>Add Recipe</button>
      <button type="submit">Save Meal Plan</button>
    </form>
  );
}

export default MealPlanForm;
