import React from 'react';
import '../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function SignUp() {
  return (
    <Form className="signup-form">
      <h1 class="font-weight-bold" id="heading">Sign Up</h1>

      <FormGroup>
        <Label>First Name</Label>
        <Input type="text" placeholder="First Name"></Input>
      </FormGroup>

      <FormGroup>
        <Label>Last Name</Label>
        <Input type="text" placeholder="Last Name"></Input>
      </FormGroup>

      <FormGroup>
        <Label>Email</Label>
        <Input type="email" placeholder="Email"></Input>
      </FormGroup>

      <FormGroup>
        <Label>Contact Number</Label>
        <Input type="text" placeholder="Contact Number"></Input>
      </FormGroup>

      <FormGroup>
        <Label>Address</Label>
        <Input type="text" placeholder="Address"></Input>
      </FormGroup>

      <FormGroup>
        <Label>Gender</Label>
        <br />
        <select id="gender" name="gender">
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Potato">Potato</option>
        </select>
      </FormGroup>

      <FormGroup>
        <Label>Date of Birth</Label>
        <Input type="date" placeholder="DOB"></Input>
      </FormGroup>

      <FormGroup>
        <Label>Password</Label>
        <Input type="password" placeholder="Password"></Input>
      </FormGroup>

      <Button className="btn-lg btn-success btn-block mt-5 mb-3">
        Log In
      </Button>

      <div className="text-center">
        <a href="/sign-in">Sign up</a>
        <span className="p-2">|</span>
        <a href="/">Forgot Password</a>
      </div>


    </Form>
  );
}

export default SignUp;
