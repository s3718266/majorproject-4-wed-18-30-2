import React from 'react';
import '../../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import config from '../../Constants';

class AssignWorker extends React.Component {

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    showErrorModal(msg) {

        const node = document.getElementById('errorMessage');
        node.innerHTML = msg;
        node.classList.remove('d-none');

    }

    showServiceCreated(msg) {

        const node = document.getElementById('successMessage');
        node.innerHTML = msg;
        node.classList.remove('d-none');

    }

    handleLoginResponse(resp) {

        if (typeof resp.error != "undefined") {
            this.showErrorModal(resp.error);
        } else {
            this.showServiceCreated("Service is created");
            window.location = "admin-dashboard"
        }

    }

    handleSubmit(event) {

        event.preventDefault();
        var sid = document.getElementById('service-id').value;
        var uid = document.getElementById('user-id').value;


        const data = encodeURI('auth-token=' + localStorage.getItem('auth_token') + '&service-id=' + sid + "&user-id=" + uid);

        fetch(config.APP_URL + 'service/assignworker', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: data
        })
            .then(res => res.json())
            .then(res => this.handleLoginResponse(res))

    }

    render() {

        return (

            
            <Form className="login-form" onSubmit={this.handleSubmit}>
                <h1 className="font-weight-bold" id="heading">Assign Worker</h1>
                <div className="alert alert-danger d-none" id="errorMessage">
                </div>
                <div className="alert alert-success d-none" id="successMessage"></div>

                <FormGroup>
                    <Label>Service ID</Label>
                    <Input type="text" id="service-id" name="service-id" placeholder="Service ID" ref={node => (this.serviceid = node)}></Input>
                </FormGroup>

                <FormGroup>
                    <Label>User ID</Label>
                    <Input type="text" id="user-id" name="user-id" placeholder="User ID" ref={node => (this.userid = node)}></Input>
                </FormGroup>

                <Button className="btn-lg btn-success btn-block mt-4 mb-3">
                    Add Service
                 </Button>


            </Form>
        );

    }

}

export default AssignWorker;
