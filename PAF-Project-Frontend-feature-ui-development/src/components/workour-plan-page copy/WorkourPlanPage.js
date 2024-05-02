import React from 'react'
import Middle from '../middle/MiddleWorkoutPlan';
import Left from '../left/Left';
import Right from '../right/Right';
import Header from '../header/Header';
import "./WorkourPlanPage.css";
export default function WorkourPlanPage ({ authenticated, onLogout }) {
  return (
    <div>
        <Header authenticated={authenticated} onLogout={onLogout} />
    <main>
       <div className='container'>
          <Left />
          <Middle />
       </div>
    </main>
    </div>
    
  )
}
