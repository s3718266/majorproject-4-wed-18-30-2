import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, BrowserRouter as Router, Switch} from "react-router-dom"
import SignIn from './pages/sign-in';
import SignUp from './pages/sign-up';
import Home from './pages/home';

function App() {
  return (
    <div className="container">

      <Router>

        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/sign-in" component={SignIn} />
          <Route path="/sign-up" component={SignUp} />
        </Switch>

      </Router>

    </div>
  );
}

export default App;
