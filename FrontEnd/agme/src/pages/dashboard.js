import React from 'react';
import '../App.css';
import {Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function Dashboard() {
  return (

    <Form className="dashboard-form">

      <h1 className="font-weight-bold" id="heading">Dashboard</h1>

      <FormGroup>
        <Label>First Name</Label>
        <Input type="text" placeholder="First Name" disabled></Input>
      </FormGroup>

      <FormGroup>
        <Label>Last Name</Label>
        <Input type="text" placeholder="Last Name" disabled></Input>
      </FormGroup>

      <FormGroup>
        <Label>Email</Label>
        <Input type="email" placeholder="Email" disabled></Input>
      </FormGroup>

      
    </Form>
  );
}

export default Dashboard;
