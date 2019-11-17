import React from 'react';
import logo from './logo.svg';
import './App.css';
import Notifications from './Notifications'

const App: React.FC = () => {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <Notifications />
      </header>
    </div>
  );
}

export default App;
