import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, BrowserRouter as Router, Switch } from "react-router-dom"
import SignIn from './pages/sign-in';
import SignUp from './pages/sign-up';
import Profile from './pages/profile';
import Home from './pages/home';
import Dashboard from './pages/dashboard';


function App() {
  return (
    <div id="main-container">
      <div id="content-wrap">
        <Router>

          <Switch>
            <Route path="/" exact component={Home} />
            <Route path="/sign-in" component={SignIn} />
            <Route path="/sign-up" component={SignUp} />
            <Route path="/profile" component={Profile} />
            <Route path="/dashboard" component={Dashboard} />

          </Switch>

        </Router>
      </div>
    </div>
  );
}

export default App;
