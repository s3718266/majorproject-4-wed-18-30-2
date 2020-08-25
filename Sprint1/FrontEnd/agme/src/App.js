import React from 'react';
import './App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="login-form">
      <h1 class="font-weight-bold" id="heading">Sign In</h1>

      <FormGroup>
        <Label>Email</Label>
        <Input type="email" placeholder="Email"></Input>
      </FormGroup>

      <FormGroup>
        <Label>Password</Label>
        <Input type="password" placeholder="Password"></Input>
      </FormGroup>

      <Button button="btn-lg">
        Log In
      </Button>


    </div>
  );
}

export default App;
