import React from 'react';
import '../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function Profile() {
  return (
    <div>
    <Form className="signup-form">
      <h1 class="font-weight-bold" id="heading">Profile</h1>

      <div className="row">

      <div className="col-6">
      <FormGroup>
        <Label>First Name</Label>
        <Input type="text" placeholder="First Name" value="Sample"></Input>
      </FormGroup>
      </div>

      <div className="col-6">
      <FormGroup>
        <Label>Last Name</Label>
        <Input type="text" placeholder="Last Name" value="Sample"></Input>
      </FormGroup>
      </div>

      </div>

      <FormGroup>
        <Label>Contact Number</Label>
        <Input type="text" placeholder="Contact Number" value="Sample"></Input>
      </FormGroup>

      <FormGroup>
        <Label>Address</Label>
        <Input type="text" placeholder="Address" value="Sample"></Input>
      </FormGroup>

      <Button className="btn-lg btn-success btn-block mt-5 mb-3">
        Save Changes
      </Button>

    </Form>
    
    <Form className="signup-form">

      <FormGroup>
        <Label>Current Password</Label>
        <Input type="password" placeholder="*******" value=""></Input>
      </FormGroup>

      <FormGroup>
        <Label>New Password</Label>
        <Input type="password" placeholder="*******" value=""></Input>
      </FormGroup>

      <Button className="btn-lg btn-success btn-block mt-5 mb-3">
        Change Password
      </Button>

    </Form>
    </div>
  );
}

export default Profile;
