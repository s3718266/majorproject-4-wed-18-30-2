import React from 'react';
import '../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function Dashboard() {
  return (

    <Form className="dashboard-form">

        <li className="nav-item">
            <a className="navbar-brand nav justify-content-center" href="/sign-in">Dashboard</a>
        </li>

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
