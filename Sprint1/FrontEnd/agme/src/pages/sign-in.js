import React from 'react';
import '../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function SignIn() {
  return (
    <Form className="login-form">
        <h1 class="font-weight-bold" id="heading">Sign In</h1>

        <FormGroup>
          <Label>Email</Label>
          <Input type="email" placeholder="Email"></Input>
        </FormGroup>

        <FormGroup>
          <Label>Password</Label>
          <Input type="password" placeholder="Password"></Input>
        </FormGroup>

        <Button className="btn-lg btn-success btn-block mt-4 mb-3">
          Log In
      </Button>

        <div className="text-center">
          <a href="/sign-up">Sign up</a>
          <span className="p-2">|</span>
          <a href="/sign-up">Forgot Password</a>
        </div>


      </Form>
  );
}

export default SignIn;
