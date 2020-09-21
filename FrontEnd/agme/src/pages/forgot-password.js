import React from 'react';
import '../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import config from '../Constants';

class ForgotPassword extends React.Component {

  constructor() {
    super();
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {

    document.getElementById('successMessage').classList.add('d-none');
    document.getElementById('errorMessage').classList.add('d-none');

    event.preventDefault();
    const data = new URLSearchParams(new FormData(event.target));

    fetch(config.APP_URL + 'auth/passwordreset', {
      method: 'POST',
      body: data
    })
      .then(
        function (res) {

          res.text().then(function (data) {

            try {

              var json = JSON.parse(data);

              const node = document.getElementById('errorMessage');
              node.innerHTML = json.error;
              node.classList.remove('d-none');

            } catch (e) {

              const node = document.getElementById('successMessage');
              node.innerHTML = data;
              node.classList.remove('d-none');

            }

          });

        }

      )

  }

  render() {

    return (
      <Form className="signup-form" onSubmit={this.handleSubmit} data-testid="form">
        <h1 className="font-weight-bold" id="heading">Forgot Password</h1>

        <div className="alert alert-danger d-none" id="errorMessage">

        </div>

        <div className="alert alert-success d-none" id="successMessage">

        </div>

        <FormGroup>
          <Label>Email</Label>
          <Input id="email" type="email" name="email" placeholder="Email" ref={node => (this.email = node)}></Input>
        </FormGroup>

        <Button className="btn-lg btn-success btn-block mt-5 mb-3" type="submit">
          Forgot Password
        </Button>

        <div className="text-center">
          <a href="/sign-up">Sign up</a>
          <span className="p-2">|</span>
          <a href="/forgot-password">Forgot Password</a>
        </div>


      </Form>
    );

  }

}

export default ForgotPassword;
