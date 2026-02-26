import React, { useState } from 'react';
import './App.css';

function App() {
  const [mealName, setMealName] = useState('');
  const [meals, setMeals] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
//hiii
  const handleSearch = async (e) => {
    e.preventDefault();
    if (!mealName.trim()) return;

    setLoading(true);
    setError('');
    try {
      const response = await fetch(
        `${process.env.REACT_APP_API_URL}/meals/search?name=${mealName}`
      );
      const data = await response.json();
      if (data.success && data.data) {
        setMeals(data.data);
      } else {
        setError('No meals found');
        setMeals([]);
      }
    } catch (err) {
      setError('Failed to fetch meals');
      setMeals([]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>🍽️ MealDB App</h1>
        <p>Find meals with the least ingredients!</p>
      </header>

      <main className="App-main">
        <form onSubmit={handleSearch} className="search-form">
          <input
            type="text"
            placeholder="Search for a meal (e.g., Arrabiata)..."
            value={mealName}
            onChange={(e) => setMealName(e.target.value)}
            className="search-input"
          />
          <button type="submit" className="search-button">
            Search
          </button>
        </form>

        {error && <div className="error-message">{error}</div>}

        {loading && <div className="loading">Loading meals...</div>}

        <div className="meals-container">
          {meals.length > 0 && (
            <>
              <h2>Found {meals.length} meal(s)</h2>
              <div className="meals-grid">
                {meals.map((meal) => (
                  <div key={meal.id} className="meal-card">
                    <img src={meal.image} alt={meal.name} />
                    <h3>{meal.name}</h3>
                    <p className="ingredient-count">
                      Ingredients: {meal.ingredientCount}
                    </p>
                  </div>
                ))}
              </div>
            </>
          )}
        </div>
      </main>
    </div>
  );
}

export default App;
